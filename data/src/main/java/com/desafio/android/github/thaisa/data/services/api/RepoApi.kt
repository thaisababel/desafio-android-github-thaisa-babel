package com.desafio.android.github.thaisa.data.services.api

import com.desafio.android.github.thaisa.data.services.response.repo.RepoResponse
import com.desafio.android.github.thaisa.data.services.response.GitHubBaseResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RepoApi {
    @GET("search/repositories?q=language:Java&sort=stars")
    fun getRepo(
        @Query("page") page: Int
    ): Call<RepoResponse>
}