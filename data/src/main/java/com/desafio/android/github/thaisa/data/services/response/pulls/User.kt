package com.desafio.android.github.thaisa.data.services.response.pulls

import com.google.gson.annotations.SerializedName


data class User(
    @SerializedName("login") val login: String
)