package com.example.nandoapp.data.repository

import android.util.Log
import com.example.nandoapp.data.model.Character
import com.example.nandoapp.data.network.RetrofitClient

class CharacterRepository {

    private val api = RetrofitClient.instance

    suspend fun getAllCharacters(page: Int): List<Character> {
        return try {
            api.getAllCharacters(page).results
        } catch (e: Exception) {
            Log.e("API_ERROR_REAL", "Error al traer todos: ${e.message}", e)
            emptyList()
        }
    }

    suspend fun searchCharacters(name: String): List<Character> {
        return try {
            api.searchCharacters(name).results
        } catch (e: Exception) {
            Log.e("API_ERROR_REAL", "Error al buscar $name: ${e.message}", e)
            emptyList()
        }
    }
}
