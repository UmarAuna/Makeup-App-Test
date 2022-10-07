package com.makeup.makeupapptest.koin

import com.makeup.makeupapptest.repository.repo.MakeUpRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { MakeUpRepository() }
}