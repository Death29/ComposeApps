package com.example.testindocyber.model.network.room

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomInjection{

    @Provides
    fun provideDataUser(appDb: AppDatabase): InformationUserDao{
        return appDb.dataUser()
    }

    @Provides
    fun provideListPayment(appDb: AppDatabase): HistoryPaymentDao{
        return appDb.listPayment()
    }

    @Provides
    @Singleton
    fun provideAppDb(@ApplicationContext context: Context): AppDatabase{
        return Room.databaseBuilder(
            context, AppDatabase::class.java, "assessment"
        ).build()
    }
}