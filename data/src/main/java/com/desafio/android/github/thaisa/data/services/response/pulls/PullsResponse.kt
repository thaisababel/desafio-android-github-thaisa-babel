package com.desafio.android.github.thaisa.data.services.response.pulls

import com.google.gson.annotations.SerializedName

data class PullsResponse (

	@SerializedName("user") val user : User,
	@SerializedName("title") val title : String,
	@SerializedName("body") val body : String,
	@SerializedName("created_at") val created_at : String
)