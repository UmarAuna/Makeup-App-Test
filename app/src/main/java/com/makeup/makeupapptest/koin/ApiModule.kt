package com.makeup.makeupapptest.koin

import com.makeup.makeupapptest.repository.remote.MakeUpApiService
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    fun provideMakeUpApi(retrofit: Retrofit): MakeUpApiService {
        return retrofit.create(MakeUpApiService::class.java)
    }

    single { provideMakeUpApi(get()) }
}
