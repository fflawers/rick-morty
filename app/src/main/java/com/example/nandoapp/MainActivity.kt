package com.example.nandoapp

import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nandoapp.data.repository.CharacterRepository
import com.example.nandoapp.ui.CharacterAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val repository = CharacterRepository()
    private lateinit var recyclerView: RecyclerView
    private lateinit var etSearch: EditText

    // Variables de control para el scroll infinito
    private var currentPage = 1
    private var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rvCharacters)
        etSearch = findViewById(R.id.etSearch)

        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (dy > 0) { // Si hace scroll hacia abajo
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val visibleItemCount = layoutManager.childCount
                    val totalItemCount = layoutManager.itemCount
                    val pastVisibleItems = layoutManager.findFirstVisibleItemPosition()

                    if (!isLoading && (visibleItemCount + pastVisibleItems) >= totalItemCount) {
                        isLoading = true
                        currentPage++
                        cargarSiguientePagina()
                    }
                }
            }
        })

        // Cargamos todos los personajes al abrir la app son 20 por defecto segun recuerdo
        cargarPersonajes(query = "")

        // buscador
        etSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val textoBusqueda = etSearch.text.toString()

                if (textoBusqueda.isEmpty()) {
                    Toast.makeText(this, "Ingresa un nombre para buscar", Toast.LENGTH_SHORT).show()
                    currentPage = 1 // Reiniciamos la página si se borra la búsqueda
                    cargarPersonajes(query = "")
                } else {
                    cargarPersonajes(query = textoBusqueda)
                }
                true
            } else {
                false
            }
        }
    }

    private fun cargarSiguientePagina() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val nuevosPersonajes = repository.getAllCharacters(currentPage)

                runOnUiThread {
                    if (nuevosPersonajes.isNotEmpty()) {
                        val adapter = recyclerView.adapter as CharacterAdapter
                        adapter.addCharacters(nuevosPersonajes)
                    }
                    isLoading = false
                }
            } catch (e: Exception) {
                Log.e("API_ERROR", "Error al cargar la página $currentPage", e)
                isLoading = false
            }
        }
    }

    private fun cargarPersonajes(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val characters = if (query.isEmpty()) {
                    repository.getAllCharacters(1)
                } else {
                    repository.searchCharacters(query)
                }

                runOnUiThread {
                    if (characters.isEmpty()) {
                        Log.e("API_ERROR", "¡La lista llegó vacía!")
                    }
                    recyclerView.adapter = CharacterAdapter(characters.toMutableList())
                }
            } catch (e: Exception) {
                Log.e("API_ERROR", "Explotó la llamada a la API: ${e.message}")
            }
        }
    }
}