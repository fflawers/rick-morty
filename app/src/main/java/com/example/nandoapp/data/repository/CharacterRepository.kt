package com.example.nandoapp.data.repository

import com.example.nandoapp.data.model.Character
import com.example.nandoapp.data.network.RetrofitClient

class CharacterRepository {

    private val api = RetrofitClient.instance

    suspend fun getAllCharacters(): List<Character> {
        return try {
            api.getAllCharacters().results
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun searchCharacters(name: String): List<Character> {
        return try {
            api.searchCharacters(name).results
        } catch (e: Exception) {
            emptyList()
        }
    }
}