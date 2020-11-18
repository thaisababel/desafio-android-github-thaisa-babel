package com.desafio.android.github.thaisa.data.services

import com.desafio.android.github.thaisa.data.GitHubRequestGenerator
import com.desafio.android.github.thaisa.data.mapper.PullsMapperService
import com.desafio.android.github.thaisa.data.services.api.PullsApi
import com.desafio.android.github.thaisa.domain.entities.Pulls
import com.desafio.android.github.thaisa.domain.utils.Result

class PullsService {

    private val api: GitHubRequestGenerator = GitHubRequestGenerator()
    private val mapper: PullsMapperService = PullsMapperService()

    fun getPulls(owner: String, repo: String): Result<List<Pulls>> {
        val callResponse = api.createService(PullsApi::class.java).getPulls(owner, repo)
        val response = callResponse.execute()
        if (response != null) {
            if (response.isSuccessful) {
                response.body()?.let {

                    it?.let { it1 -> mapper.transform(it1) }
                }?.let {
                    return Result.Success(it)
                }
                return Result.Failure(Exception(response.message()))
            }
        }
        return Result.Failure(Exception("Bad request/response"))
    }


}

