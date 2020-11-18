package com.desafio.android.github.thaisa.data.services.response

class GitHubBaseResponse<T>(
    var code: Int,
    var status: String,
    var data: T?
)
