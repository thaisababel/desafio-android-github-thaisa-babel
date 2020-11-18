package com.desafio.android.github.thaisa.babel

import android.app.Application
import com.desafio.android.github.thaisa.babel.di.useCasesRepoModule
import com.desafio.android.github.thaisa.babel.di.useCasesPullsModule
import com.desafio.android.github.thaisa.babel.di.viewModelPullModule
import com.desafio.android.github.thaisa.babel.di.viewModelRepoModule
import com.desafio.android.github.thaisa.di.repositoriesModule
import io.realm.Realm
import org.koin.core.context.startKoin

class GitHubApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)

        startKoin {
            modules(
                listOf(
                    repositoriesModule,
                    viewModelRepoModule,
                    viewModelPullModule,
                    useCasesRepoModule,
                    useCasesPullsModule
                )
            )
        }
    }
}
