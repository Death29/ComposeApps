package com.example.assessmenttest.ui.screen.home.qr.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testindocyber.model.network.room.HistoryPaymentDao
import com.example.testindocyber.model.network.room.InformationUser
import com.example.testindocyber.model.network.room.InformationUserDao
import com.example.testindocyber.model.network.room.Payment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelLocal @Inject constructor(val repo: InformationUserDao ):ViewModel() {
    val dataUser : Flow<InformationUser> = repo.getDataUser()
    fun insertDataUser(data: InformationUser) = viewModelScope.launch {
        repo.insertDataUser(data)
    }
    fun updateDataUser(data: InformationUser) = viewModelScope.launch {
        repo.updateData(data)
    }
}

@HiltViewModel
class ViewModelDataPayment @Inject constructor(private val repoPayment: HistoryPaymentDao): ViewModel(){
    val dataPayment: Flow<List<Payment>> = repoPayment.getListPayment()
    fun insertPayment(payment: List<Payment>) = viewModelScope.launch {
        repoPayment.insertAll(payment)
    }
}