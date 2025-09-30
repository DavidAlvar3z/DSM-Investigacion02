package com.example.investigacion02_dsm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load

class PokemonAdapter(
    private val onPokemonClick: (String) -> Unit
) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    private val pokemonList = mutableListOf<PokemonBasic>()
    private var currentOffset = 0

    inner class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivPokemon: ImageView = itemView.findViewById(R.id.ivPokemon)
        private val tvNumber: TextView = itemView.findViewById(R.id.tvNumber)
        private val tvName: TextView = itemView.findViewById(R.id.tvName)
        private val tvTypes: TextView = itemView.findViewById(R.id.tvTypes)

        fun bind(pokemon: PokemonBasic) {
            // Obtener ID real desde la URL
            val pokemonId = pokemon.url
                .trimEnd('/')
                .substringAfterLast('/')
                .toInt()

            // Nombre capitalizado
            tvName.text = pokemon.name.replaceFirstChar { it.uppercase() }

            // Número con formato
            tvNumber.text = String.format("#%03d", pokemonId)

            // Ocultar tipos por ahora
            tvTypes.visibility = View.GONE

            // Imagen oficial
            val imageUrl =
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$pokemonId.png"
            ivPokemon.load(imageUrl) {
                crossfade(true)
                placeholder(android.R.drawable.ic_menu_gallery)
            }

            // Click listener
            itemView.setOnClickListener {
                onPokemonClick(pokemonId.toString())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pokemon, parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(pokemonList[position])
    }

    override fun getItemCount(): Int = pokemonList.size

    fun addPokemon(newPokemon: List<PokemonBasic>) {
        val startPosition = pokemonList.size
        pokemonList.addAll(newPokemon)
        notifyItemRangeInserted(startPosition, newPokemon.size)
    }

    fun getCurrentOffset(): Int = pokemonList.size
}