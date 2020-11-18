package com.desafio.android.github.thaisa.domain.repositories

import com.desafio.android.github.thaisa.domain.entities.Repo
import com.desafio.android.github.thaisa.domain.utils.Result


interface RepoRepository {
    fun getRepoGitHub(page: Int):  Result<MutableList<Repo>>

}