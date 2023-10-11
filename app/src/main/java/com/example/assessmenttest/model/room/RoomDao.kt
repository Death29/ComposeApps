package com.example.testindocyber.model.network.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [InformationUser::class, Payment::class], version = 1)
abstract class AppDatabase: RoomDatabase(){
    abstract fun dataUser(): InformationUserDao
    abstract fun listPayment(): HistoryPaymentDao
}