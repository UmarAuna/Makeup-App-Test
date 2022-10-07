package com.makeup.makeupapptest.koin

import android.app.Application
import androidx.room.Room
import com.makeup.makeupapptest.repository.local.MakeUpDatabase
import com.makeup.makeupapptest.util.Constant
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    fun provideDatabase(application: Application): MakeUpDatabase {
        return Room.databaseBuilder(application, MakeUpDatabase::class.java, Constant.makeupDb)
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideMakeUpDAO(database: MakeUpDatabase) = database.makeUpDAO

    single { provideDatabase(androidApplication()) }
    single { provideMakeUpDAO(get()) }
}
