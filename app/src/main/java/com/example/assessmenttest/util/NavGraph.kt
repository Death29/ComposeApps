package com.example.assessmenttest.util

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import co.yml.charts.ui.piechart.models.PieChartData
import com.example.assessmenttest.model.network.DataChart
import com.example.assessmenttest.model.network.DataDonutChart
import com.example.assessmenttest.ui.screen.SplashScreen
import com.example.assessmenttest.ui.screen.home.HomeScreen
import com.example.assessmenttest.ui.screen.home.chart.ChartScreen
import com.example.assessmenttest.ui.screen.home.chart.detail.DetailChartScreen
import com.example.assessmenttest.ui.screen.home.promo.ListPromoScreen
import com.example.assessmenttest.ui.screen.home.promo.detail.DetailPromoScreen
import com.example.assessmenttest.ui.screen.home.promo.util.ViewModelListPromo
import com.example.assessmenttest.ui.screen.home.qr.QrScreen
import com.example.assessmenttest.ui.screen.home.qr.payment.PaymentScreen
import com.example.assessmenttest.ui.screen.home.qr.util.ViewModelDataPayment
import com.example.assessmenttest.ui.screen.home.qr.util.ViewModelLocal
import com.example.assessmenttest.ui.screen.home.transaction.HistoryTransactionScreen
import com.example.assessmenttest.util.itempromo.PromoItem

object DestinationApps {
    const val SPLASH_SCREEN = "splash_screen"
    const val HOME_VIEW = "home_view"
    const val PROMO_VIEW = "promo_view"
    const val DETAIL_PROMO_VIEW = "detail_promo_view"
    const val HISTORY_TRANSACTION_VIEW = "transaction_view"
    const val CHART_VIEW = "chart_view"
    const val DETAIL_CHART_VIEW = "detail_chart_view"
    const val QR_VIEW = "qr_view"
    const val DETAIL_PAYMENT_VIEW = "detail_payment_view"
}

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    finishActivity: () -> Unit = {},
    navController: NavHostController = rememberNavController(),
    startDestination: String = DestinationApps.SPLASH_SCREEN
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(DestinationApps.SPLASH_SCREEN) {
            BackHandler {
                finishActivity()
            }
            SplashScreen {
                val navBuilder = NavOptions.Builder()
                    .setPopUpTo(DestinationApps.SPLASH_SCREEN, true)
                    .build()
                navController.navigate(DestinationApps.HOME_VIEW, navBuilder)
            }
        }

        composable(route = DestinationApps.HOME_VIEW) {
            BackHandler {
                finishActivity()
            }

            HomeScreen(
                onPromoClick = { navController.navigate(DestinationApps.PROMO_VIEW) },
                onHistoryTransactionClick = { navController.navigate(DestinationApps.HISTORY_TRANSACTION_VIEW) },
                onScanQrClick = { navController.navigate(DestinationApps.QR_VIEW) },
                onChartClick = { navController.navigate(DestinationApps.CHART_VIEW) })
        }

        composable(route = DestinationApps.PROMO_VIEW) {
            BackHandler {
                navController.navigateUp()
            }

            val viewModel: ViewModelListPromo = hiltViewModel()
            ListPromoScreen(viewModel = viewModel,
                onDetailPromo = {
                    navController.currentBackStackEntry?.savedStateHandle?.set("promo", it)
                    navController.navigate(DestinationApps.DETAIL_PROMO_VIEW)
                })
        }

        composable(route = DestinationApps.DETAIL_PROMO_VIEW) {
            BackHandler {
                navController.navigateUp()
            }
            val promo =
                navController.previousBackStackEntry?.savedStateHandle?.get<PromoItem>("promo")
            if (promo != null) {
                DetailPromoScreen(promo = promo)
            }
        }

        composable(route = DestinationApps.CHART_VIEW) {
            BackHandler {
                navController.navigateUp()
            }

            ChartScreen(onDetailChart = {
                Log.d("dataSlice", it.toString())
                navController.currentBackStackEntry?.savedStateHandle?.set("donutChart", it)
                navController.navigate(DestinationApps.DETAIL_CHART_VIEW)
            })
        }

        composable(route = DestinationApps.DETAIL_CHART_VIEW) {
            BackHandler {
                navController.navigateUp()
            }
            val dataDonut =
                navController.previousBackStackEntry?.savedStateHandle?.get<DataDonutChart>("donutChart")
            if (dataDonut != null) {
                Log.d("dataSlice", dataDonut.toString())
                DetailChartScreen(data = dataDonut)
            }
        }

        composable(route = DestinationApps.QR_VIEW) {
            BackHandler {
                navController.navigateUp()
            }

            QrScreen(onQrResult = {
                navController.currentBackStackEntry?.savedStateHandle?.set("qr", it)
                navController.navigate(DestinationApps.DETAIL_PAYMENT_VIEW)
            })
        }

        composable(route = DestinationApps.DETAIL_PAYMENT_VIEW) {
            BackHandler {
                navController.navigateUp()
            }

            val dataQr = navController.previousBackStackEntry?.savedStateHandle?.get<String>("qr")
            val viewModel: ViewModelLocal = hiltViewModel()
            val viewModelPayment: ViewModelDataPayment = hiltViewModel()

            if (dataQr != null) {
                PaymentScreen(
                    data = dataQr,
                    onSuccessPayment = {
                        navController.navigate(DestinationApps.HOME_VIEW)
                    },
                    viewModel = viewModel,
                    viewModelPayment = viewModelPayment
                )
            }
        }
        
        composable(route = DestinationApps.HISTORY_TRANSACTION_VIEW){
            BackHandler {
                navController.navigateUp()
            }

            val viewModelPayment: ViewModelDataPayment = hiltViewModel()
            HistoryTransactionScreen(viewModelPayment = viewModelPayment)
            
        }
    }
}