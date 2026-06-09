package com.example.nandoapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nandoapp.R
import com.example.nandoapp.data.model.Character
import android.content.Intent
import android.widget.ImageView
import coil.load
import com.example.nandoapp.ui.detail.DetailActivity

class CharacterAdapter(private val characters: MutableList<Character>) :
RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    // "ViewHolder" es la que amarra el XML con las variables de Kotlin
    class CharacterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvName)
        val tvStatus: TextView = view.findViewById(R.id.tvStatus)
        val ivPhoto: ImageView = view.findViewById(R.id.ivCharacterPhoto)
    }
    // 1. Aquí se dibuja/manda el XML de la fila
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_character, parent, false)
        return CharacterViewHolder(view)
    }
    // 2. Aquí se llena cada fila cuadro o eso con los datos del JSON de la api
    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = characters[position]

        holder.tvName.text = character.name
        holder.tvStatus.text = "${character.status} - ${character.species}"
        holder.ivPhoto.load(character.image) {
            crossfade(true) // Le da un efectito de desvanecimiento al aparecer
        }

        holder.itemView.setOnClickListener { view ->
            val intent = android.content.Intent(view.context, DetailActivity::class.java)

            // datos de cada personaje
            intent.putExtra("EXTRA_NAME", character.name)
            intent.putExtra("EXTRA_STATUS", character.status)
            intent.putExtra("EXTRA_SPECIES", character.species)
            intent.putExtra("EXTRA_IMAGE", character.image)
            intent.putExtra("EXTRA_ORIGIN_NAME", character.origin.name)
            intent.putExtra("EXTRA_LOCATION_NAME", character.location.name)
            intent.putExtra("EXTRA_EPISODES_COUNT", character.episode.size.toString())

            val todosLosEpisodios = character.episode.joinToString(separator = "\n")
            intent.putExtra("EXTRA_EPISODES", todosLosEpisodios)
            view.context.startActivity(intent)
        }


    }
    // cuántos elementos hay en total
    override fun getItemCount(): Int = characters.size

    fun addCharacters(newCharacters: List<Character>) {
        val posicionInicial = characters.size // ¿Dónde nos quedamos?
        characters.addAll(newCharacters)      // Metemos los nuevos a la lista

        notifyItemRangeInserted(posicionInicial, newCharacters.size)
    }
}