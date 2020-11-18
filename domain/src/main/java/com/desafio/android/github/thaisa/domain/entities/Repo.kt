package com.desafio.android.github.thaisa.domain.entities


val NOT_FOUND = "NOT FOUND"
val DEFAULT_ID = 0

class Repo(
    val nameRepo: String = NOT_FOUND,
    val description: String = NOT_FOUND,
    val nameOwner: String = NOT_FOUND,
    val photoOwner: String = NOT_FOUND,
    val numberStars: Int = DEFAULT_ID,
    val forks: Int = DEFAULT_ID
)
