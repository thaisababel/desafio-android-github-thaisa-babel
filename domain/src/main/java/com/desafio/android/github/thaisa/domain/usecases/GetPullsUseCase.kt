package com.desafio.android.github.thaisa.domain.usecases

import com.desafio.android.github.thaisa.domain.repositories.PullsRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

class GetPullsUseCase : KoinComponent {
    private val pullRepository: PullsRepository by inject()
    operator fun invoke(owner: String, repo: String) =
        pullRepository.getPulls(owner, repo)
}
