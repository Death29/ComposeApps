package com.example.testindocyber.model.network.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "information_user")
data class InformationUser(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val saving: Double
)

@Entity(tableName = "list_payment")
data class Payment(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nameTransaction: String,
    val amount: Double
)