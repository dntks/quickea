package com.dtks.quickea.di

import android.content.Context
import androidx.room.Room
import com.dtks.quickea.data.local.CartDao
import com.dtks.quickea.data.local.ProductDao
import com.dtks.quickea.data.local.QuickeaDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): QuickeaDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            QuickeaDatabase::class.java,
            "Quickea.db"
        ).build()
    }

    @Provides
    fun provideProductDao(database: QuickeaDatabase): ProductDao = database.productDao()

    @Provides
    fun provideCartDao(database: QuickeaDatabase): CartDao = database.cartDao()

}
