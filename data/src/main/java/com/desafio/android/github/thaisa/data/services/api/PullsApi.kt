package com.desafio.android.github.thaisa.data.services.api

import com.desafio.android.github.thaisa.data.services.response.pulls.PullsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PullsApi {
    @GET("/repos/{owner}/{repo}/pulls")
    fun getPulls(
        @Path("owner") owner: String, @Path("repo") repo: String
    ): Call<List<PullsResponse>>

}