package com.desafio.android.github.thaisa.di

import com.desafio.android.github.thaisa.data.repositories.PullsRepositoryImpl
import com.desafio.android.github.thaisa.data.repositories.RepoRepositoryImpl
import com.desafio.android.github.thaisa.data.services.PullsService
import com.desafio.android.github.thaisa.data.services.RepoService
import com.desafio.android.github.thaisa.domain.repositories.PullsRepository
import com.desafio.android.github.thaisa.domain.repositories.RepoRepository
import org.koin.dsl.module

val repositoriesModule = module {
    single { RepoService() }
    single { PullsService() }
    single<RepoRepository> { RepoRepositoryImpl(get()) }
    single<PullsRepository> { PullsRepositoryImpl(get()) }
}