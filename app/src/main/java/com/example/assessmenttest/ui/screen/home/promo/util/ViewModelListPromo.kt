package com.example.assessmenttest.ui.screen.home.promo.util

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assessmenttest.model.response.StateResponse
import com.example.assessmenttest.model.AssessmentRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelListPromo @Inject constructor(val repository: AssessmentRepo) : ViewModel() {

    private val _listPromo: MutableLiveData<PromoState> = MutableLiveData()
    val promo: LiveData<PromoState> = _listPromo

    init {
        getPromo()
    }

    private fun getPromo() {
        viewModelScope.launch {
            repository.getListPromo().collect {
                when (it) {
                    is StateResponse.Loading -> {
                        _listPromo.postValue(
                            PromoState(
                                isLoading = true,
                                isError = false
                            )
                        )
                    }

                    is StateResponse.SuccessGetData -> {
                        _listPromo.postValue(
                            PromoState(
                                promo = it.data,
                                isLoading = false,
                                isError = false
                            )
                        )
                    }

                    is StateResponse.FailedGetData -> {
                        _listPromo.postValue(
                            PromoState(
                                isLoading = false,
                                isError = true
                            )
                        )
                    }
                }
            }
        }
    }
}