package com.example.testindocyber.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testindocyber.util.itempromo.GetPromoCase
import com.example.testindocyber.util.itempromo.PromoItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelListPromo @Inject constructor(private val getPromoCase: GetPromoCase): ViewModel() {

    private val _promo = MutableStateFlow(emptyList<PromoItem>())

    val promo: StateFlow<List<PromoItem>> get() = _promo

    init {
        getPromo()
    }

    private fun getPromo() {
        viewModelScope.launch {
            try {
                val promo = getPromoCase()
                _promo.value = promo
            }catch (e: Exception){}
        }
    }
}