package com.example.testindocyber.model.network.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable

@Dao
interface InformationUserDao{
    @Query("SELECT * FROM information_user")
    fun getDataUser(): Flow<InformationUser>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDataUser(data: InformationUser)

    @Update
    suspend fun updateData(data: InformationUser)
}

@Dao
interface HistoryPaymentDao{
    @Query("SELECT * FROM list_payment")
    fun getListPayment(): Flow<List<Payment>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(payment: List<Payment>)

}