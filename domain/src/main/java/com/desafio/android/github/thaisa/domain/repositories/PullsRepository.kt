package com.desafio.android.github.thaisa.domain.repositories

import com.desafio.android.github.thaisa.domain.utils.Result
import com.desafio.android.github.thaisa.domain.entities.Pulls


interface PullsRepository {
    fun getPulls(owner: String, repo: String): Result<List<Pulls>>
}