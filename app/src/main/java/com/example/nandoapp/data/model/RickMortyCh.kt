package com.example.nandoapp.data.model

data class CharacterResponse(
    val info: Info,
    val results: List<Character>
)

data class Info(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)

data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val image: String,
    val origin: LocationData,
    val location: LocationData,
    val episode: List<String>
)

data class LocationData(
    val name: String,
    val url: String
)
