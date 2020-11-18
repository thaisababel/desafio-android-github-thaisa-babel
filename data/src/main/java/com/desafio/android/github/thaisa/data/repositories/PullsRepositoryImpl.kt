package com.desafio.android.github.thaisa.data.repositories

import com.desafio.android.github.thaisa.data.services.PullsService
import com.desafio.android.github.thaisa.domain.entities.Pulls
import com.desafio.android.github.thaisa.domain.repositories.PullsRepository
import com.desafio.android.github.thaisa.domain.utils.Result

class PullsRepositoryImpl(
    private val pullsService: PullsService
) : PullsRepository {
    override fun getPulls(owner: String, repo: String): Result<List<Pulls>> =
        pullsService.getPulls(owner, repo)

}