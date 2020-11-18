package com.desafio.android.github.thaisa.data.services

import com.desafio.android.github.thaisa.data.GitHubRequestGenerator
import com.desafio.android.github.thaisa.data.mapper.RepoMapperService
import com.desafio.android.github.thaisa.data.services.api.RepoApi
import com.desafio.android.github.thaisa.domain.entities.Repo
import com.desafio.android.github.thaisa.domain.utils.Result

class RepoService {

    private val api: GitHubRequestGenerator = GitHubRequestGenerator()
    private val mapper: RepoMapperService = RepoMapperService()

    fun getRepo(page: Int): Result<MutableList<Repo>> {
        val callResponse = api.createService(RepoApi::class.java).getRepo(page)
        val response = callResponse.execute()
        if (response != null) {
            if (response.isSuccessful) {
                response.body()?.let {
                    mapper.transform(it)
                }?.let {
                    return Result.Success(it)
                }
                return Result.Failure(Exception(response.message()))
            }
        }
        return Result.Failure(Exception("Bad request/response"))
    }


}