package com.example.investigacion02_dsm

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import coil.load
import kotlinx.coroutines.launch

class PokemonDetailActivity : AppCompatActivity() {

    private val viewModel: PokemonViewModel by viewModels()

    private lateinit var btnBack: ImageButton
    private lateinit var tvNumber: TextView
    private lateinit var tvName: TextView
    private lateinit var tvType1: TextView
    private lateinit var tvType2: TextView
    private lateinit var ivPokemon: ImageView
    private lateinit var tvWeight: TextView
    private lateinit var tvHeight: TextView
    private lateinit var tvAbilities: TextView
    private lateinit var tvHp: TextView
    private lateinit var tvAttack: TextView
    private lateinit var tvDefense: TextView
    private lateinit var tvSpeed: TextView
    private lateinit var progressHp: ProgressBar
    private lateinit var progressAttack: ProgressBar
    private lateinit var progressDefense: ProgressBar
    private lateinit var progressSpeed: ProgressBar
    private lateinit var progressBar: ProgressBar
    private lateinit var headerBackground: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_detail)

        initViews()

        val pokemonId = intent.getStringExtra("POKEMON_ID") ?: "1"

        btnBack.setOnClickListener {
            finish()
        }

        observePokemon()
        viewModel.getPokemon(pokemonId)
    }

    private fun initViews() {
        btnBack = findViewById(R.id.btnBack)
        tvNumber = findViewById(R.id.tvNumber)
        tvName = findViewById(R.id.tvName)
        tvType1 = findViewById(R.id.tvType1)
        tvType2 = findViewById(R.id.tvType2)
        ivPokemon = findViewById(R.id.ivPokemon)
        tvWeight = findViewById(R.id.tvWeight)
        tvHeight = findViewById(R.id.tvHeight)
        tvAbilities = findViewById(R.id.tvAbilities)
        tvHp = findViewById(R.id.tvHp)
        tvAttack = findViewById(R.id.tvAttack)
        tvDefense = findViewById(R.id.tvDefense)
        tvSpeed = findViewById(R.id.tvSpeed)
        progressHp = findViewById(R.id.progressHp)
        progressAttack = findViewById(R.id.progressAttack)
        progressDefense = findViewById(R.id.progressDefense)
        progressSpeed = findViewById(R.id.progressSpeed)
        progressBar = findViewById(R.id.progressBar)
        headerBackground = findViewById(R.id.headerBackground)
    }

    private fun observePokemon() {
        lifecycleScope.launch {
            viewModel.pokemonState.collect { state ->
                when (state) {
                    is PokemonState.Loading -> {
                        progressBar.visibility = View.VISIBLE
                    }
                    is PokemonState.Success -> {
                        progressBar.visibility = View.GONE
                        displayPokemon(state.pokemon)
                    }
                    is PokemonState.Error -> {
                        progressBar.visibility = View.GONE
                        Toast.makeText(this@PokemonDetailActivity, state.message, Toast.LENGTH_SHORT).show()
                    }
                    else -> {}
                }
            }
        }
    }

    private fun displayPokemon(pokemon: PokemonResponse) {
        // Número y nombre
        tvNumber.text = String.format("#%03d", pokemon.id)
        tvName.text = pokemon.name.replaceFirstChar { it.uppercase() }

        // Tipos
        if (pokemon.types.isNotEmpty()) {
            tvType1.text = pokemon.types[0].type.name.replaceFirstChar { it.uppercase() }
            tvType1.visibility = View.VISIBLE

            if (pokemon.types.size > 1) {
                tvType2.text = pokemon.types[1].type.name.replaceFirstChar { it.uppercase() }
                tvType2.visibility = View.VISIBLE
            } else {
                tvType2.visibility = View.GONE
            }

            // Color del header según el tipo
            val typeColor = getTypeColor(pokemon.types[0].type.name)
            headerBackground.setBackgroundColor(typeColor)
        }

        // Imagen
        val imageUrl = pokemon.sprites.other?.official_artwork?.front_default
            ?: pokemon.sprites.front_default
        ivPokemon.load(imageUrl) {
            crossfade(true)
        }

        // Peso y altura
        tvWeight.text = "${pokemon.weight / 10.0} kg"
        tvHeight.text = "${pokemon.height / 10.0} m"

        // Habilidades
        val abilities = pokemon.abilities.joinToString(", ") {
            it.ability.name.replaceFirstChar { char -> char.uppercase() }
        }
        tvAbilities.text = abilities

        // Estadísticas
        pokemon.stats.forEach { stat ->
            when (stat.stat.name) {
                "hp" -> {
                    tvHp.text = stat.base_stat.toString()
                    progressHp.progress = stat.base_stat
                }
                "attack" -> {
                    tvAttack.text = stat.base_stat.toString()
                    progressAttack.progress = stat.base_stat
                }
                "defense" -> {
                    tvDefense.text = stat.base_stat.toString()
                    progressDefense.progress = stat.base_stat
                }
                "speed" -> {
                    tvSpeed.text = stat.base_stat.toString()
                    progressSpeed.progress = stat.base_stat
                }
            }
        }
    }

    private fun getTypeColor(type: String): Int {
        return when (type.lowercase()) {
            "normal" -> android.graphics.Color.parseColor("#A8A878")
            "fire" -> android.graphics.Color.parseColor("#F08030")
            "water" -> android.graphics.Color.parseColor("#6890F0")
            "electric" -> android.graphics.Color.parseColor("#F8D030")
            "grass" -> android.graphics.Color.parseColor("#78C850")
            "ice" -> android.graphics.Color.parseColor("#98D8D8")
            "fighting" -> android.graphics.Color.parseColor("#C03028")
            "poison" -> android.graphics.Color.parseColor("#A040A0")
            "ground" -> android.graphics.Color.parseColor("#E0C068")
            "flying" -> android.graphics.Color.parseColor("#A890F0")
            "psychic" -> android.graphics.Color.parseColor("#F85888")
            "bug" -> android.graphics.Color.parseColor("#A8B820")
            "rock" -> android.graphics.Color.parseColor("#B8A038")
            "ghost" -> android.graphics.Color.parseColor("#705898")
            "dragon" -> android.graphics.Color.parseColor("#7038F8")
            "dark" -> android.graphics.Color.parseColor("#705848")
            "steel" -> android.graphics.Color.parseColor("#B8B8D0")
            "fairy" -> android.graphics.Color.parseColor("#EE99AC")
            else -> android.graphics.Color.parseColor("#DC0A2D")
        }
    }
}