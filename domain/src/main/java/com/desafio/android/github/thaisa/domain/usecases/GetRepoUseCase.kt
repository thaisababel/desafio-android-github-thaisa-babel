package com.desafio.android.github.thaisa.domain.usecases

import com.desafio.android.github.thaisa.domain.repositories.RepoRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

class GetRepoUseCase: KoinComponent {
    private val repoRepository: RepoRepository by inject()
    operator fun invoke(page: Int) = repoRepository.getRepoGitHub(page)
}




