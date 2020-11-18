package com.desafio.android.github.thaisa.data.services.response.repo

import com.google.gson.annotations.SerializedName

data class Items (

	@SerializedName("name") val name : String,
	@SerializedName("owner") val owner : Owner,
	@SerializedName("description") val description : String,
	@SerializedName("stargazers_count") val stargazers_count : Int,
	@SerializedName("forks") val forks : Int
)