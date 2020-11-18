package com.desafio.android.github.thaisa.data.services.response.repo

import com.google.gson.annotations.SerializedName

data class RepoResponse(
	@SerializedName("items") var items: List<Items>?
)