package com.desafio.android.github.thaisa.domain.entities

val NOT_FOUND_PR = "NOT FOUND"
val DEFAULT_ID_PR = 0
class Pulls(
    val namePr: String = NOT_FOUND,
    val titlePr: String = NOT_FOUND,
    val datePr: String = NOT_FOUND,
    val bodyPr: String = NOT_FOUND
)
