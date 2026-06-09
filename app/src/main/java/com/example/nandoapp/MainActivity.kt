package com.example.nandoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.nandoapp.data.repository.CharacterRepository
import com.example.nandoapp.ui.components.AppButton
import com.example.nandoapp.ui.screens.HomeScreen
import com.example.nandoapp.ui.theme.NandoAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {

    private val repository = CharacterRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Prueba por consola
        CoroutineScope(Dispatchers.IO).launch {
            val characters = repository.getAllCharacters()
            characters.forEach { character ->
                println("Nombre: ${character.name} | Estado: ${character.status}")
            }
        }

        setContent {
            // tu UI por ahora vacía
        }
    }
}