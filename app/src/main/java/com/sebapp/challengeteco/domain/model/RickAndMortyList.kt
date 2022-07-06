package com.sebapp.challengeteco.domain.model


data class RickAndMortyList(
    val info: Info,
    val results: List<CharacterData>
    )

data class CharacterData(
    val name: String?,
    val status: String?,
    val species: String?,
    val gender: String?,
    val image: String?,
    val origin: Origin,
    )

data class Origin(
    val name: String?
)

data class Info(
    val count: Int?,
    val pages: String?,
    val next: String?,
    val prev: String?
    )
