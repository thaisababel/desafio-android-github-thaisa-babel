package com.desafio.android.github.thaisa.babel.di

import com.desafio.android.github.thaisa.babel.viewmodels.PullsViewModel
import com.desafio.android.github.thaisa.babel.viewmodels.RepoViewModel
import com.desafio.android.github.thaisa.domain.usecases.GetPullsUseCase
import com.desafio.android.github.thaisa.domain.usecases.GetRepoUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelPullModule = module {
    viewModel { PullsViewModel(get()) }
}

val viewModelRepoModule = module {
    viewModel { RepoViewModel(get()) }
}

val useCasesRepoModule = module {
    single { GetRepoUseCase() }
}
val useCasesPullsModule = module {
    single { GetPullsUseCase() }
}