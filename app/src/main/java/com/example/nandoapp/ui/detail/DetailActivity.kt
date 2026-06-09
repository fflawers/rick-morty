package com.example.nandoapp.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.nandoapp.R

class DetailActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // 1. Recibe datos que manda la otra pantalla
        val name = intent.getStringExtra("EXTRA_NAME") ?: "Desconocido"
        val status = intent.getStringExtra("EXTRA_STATUS") ?: "Desconocido"
        val species = intent.getStringExtra("EXTRA_SPECIES") ?: "Desconocido"
        val imageUrl = intent.getStringExtra("EXTRA_IMAGE")
        val origin = intent.getStringExtra("EXTRA_ORIGIN_NAME") ?: "Desconocido"
        val location = intent.getStringExtra("EXTRA_LOCATION_NAME") ?: "Desconocido"
        val espisodes = intent.getStringExtra("EXTRA_EPISODES_COUNT") ?: "Desconocido"

        // 2. Busca vistas
        val tvName = findViewById<TextView>(R.id.tvDetailName)
        val tvStatus = findViewById<TextView>(R.id.tvDetailStatus)
        val btnBack = findViewById<ImageButton>(R.id.btnBack)
        val ivPhoto = findViewById<ImageView>(R.id.ivDetailPhoto)
        val txtOrigin = findViewById<TextView>(R.id.Origintxt)
        val txtlocation = findViewById<TextView>(R.id.locationtxt)
        val episodeslist = findViewById<TextView>(R.id.episodeslisttxt)

        // 3. Pinta textos
        tvName.text = name
        tvStatus.text = "Estatus: $status - Especie: $species"
        ivPhoto.load(imageUrl) {
            crossfade(true)
        }
        txtOrigin.text = "Su origen es en $origin"
        txtlocation.text= "Radica en $location"
        episodeslist.text = "Aparece en $espisodes episodios"


        // 4.flecha de regreso (se chinga la pantalla donde estas y regresa)
        btnBack.setOnClickListener {
            finish()
        }
    }
}