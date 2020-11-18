package com.desafio.android.github.thaisa.data.repositories

import com.desafio.android.github.thaisa.data.services.RepoService
import com.desafio.android.github.thaisa.domain.entities.Repo
import com.desafio.android.github.thaisa.domain.repositories.RepoRepository
import com.desafio.android.github.thaisa.domain.utils.Result

class RepoRepositoryImpl(
    private val repoService: RepoService
) : RepoRepository {
    override fun getRepoGitHub(page: Int): Result<MutableList<Repo>> =
        repoService.getRepo(page)
}