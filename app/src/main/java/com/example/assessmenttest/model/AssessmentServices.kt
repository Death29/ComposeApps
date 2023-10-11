package com.example.assessmenttest.model

import androidx.room.RoomDatabase
import com.example.assessmenttest.model.response.ResponseListPromo
import com.example.assessmenttest.model.response.StateResponse
import com.example.assessmenttest.util.itempromo.PromoItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AssessmentServices @Inject constructor(
    private val services: ApiServices,
) : AssessmentRepo {
    override fun getListPromo(): Flow<StateResponse<List<PromoItem>>> = flow {
        emit(StateResponse.Loading())

        try {
            val promo = services.getListPromo()
            if (promo.code() == 200) {
                promo.body()?.let { data ->
                    emit(StateResponse.SuccessGetData(data = data, msg = promo.message()))
                }
            } else emit(StateResponse.FailedGetData(msg = promo.message()))

        } catch (e: HttpException) {
            emit(StateResponse.FailedGetData(msg = e.localizedMessage ?: "Exception Http"))
        } catch (e: IOException) {
            emit(StateResponse.FailedGetData(msg = e.localizedMessage ?: "Exception IO"))
        }
    }

}