package com.example.assessmenttest.ui.screen.home.promo

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.assessmenttes.R
import com.example.assessmenttest.ui.screen.components.LoadingComponent
import com.example.assessmenttest.ui.screen.components.TopBarComponent
import com.example.assessmenttest.ui.screen.home.promo.util.ViewModelListPromo
import com.example.assessmenttest.util.itempromo.PromoItem

@Composable
fun ListPromoScreen(
    viewModel: ViewModelListPromo,
    onDetailPromo: (PromoItem) -> Unit
) {

    //Get Data From VM
    val promoState = viewModel.promo.observeAsState()
    val promoList = mutableListOf<PromoItem>()

    promoState.value?.promo.let {
        if (it != null) {
            promoList.addAll(it)
        }
    }

    androidx.compose.material.Scaffold(
        topBar = { TopBarComponent(title = stringResource(id = R.string.title_activity_list_promo)) },
        backgroundColor = MaterialTheme.colors.surface
    ) {paddingValues->
        if (promoState.value?.isLoading == true){
            LoadingComponent(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.7f)
            )
        }else{
            LazyColumn(contentPadding = paddingValues){
                itemsIndexed(items = promoList){_, data->
                    CardListPromo(
                        modifier = Modifier.padding(horizontal = 24.dp),
                        promo = data,
                        ){ itemPromo ->
                        itemPromo?.let { onDetailPromo(data) }
                    }
                }
            }
        }
    }
}

@Composable
fun CardListPromo(
    modifier: Modifier = Modifier,
    promo: PromoItem? = null,
    onItemClicked: (PromoItem?) -> Unit
){
    Card(
        modifier = modifier
            .padding(top = 5.dp, bottom = 5.dp, start = 10.dp, end = 10.dp)
            .clickable { onItemClicked(promo) }
            .fillMaxSize(),
        shape = RoundedCornerShape(10.dp),
    ) {
        Row{
            Image(
                painter = rememberImagePainter(data = promo?.img?.url),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .padding(top = 0.dp, bottom = 0.dp, start = 10.dp, end = 10.dp)
                    .size(350.dp, 200.dp)
                    .clip(RoundedCornerShape(corner = CornerSize(10.dp)))
            )
        }
        Column(
            modifier = Modifier.padding(top =  3.dp, bottom = 3.dp, start = 10.dp, end = 10.dp)
        ) {
            Text(text = promo?.name.toString(), style = androidx.compose.material3.MaterialTheme.typography.labelLarge)
        }
    }
}