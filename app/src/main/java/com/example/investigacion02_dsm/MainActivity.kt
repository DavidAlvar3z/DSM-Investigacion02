package com.example.investigacion02_dsm

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val viewModel: PokemonViewModel by viewModels()
    private lateinit var rvPokemon: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: PokemonAdapter

    private var isLoading = false
    private val PAGE_SIZE = 20

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvPokemon = findViewById(R.id.rvPokemon)
        progressBar = findViewById(R.id.progressBar)

        setupRecyclerView()
        observePokemonList()

        // Cargar primera página
        viewModel.getPokemonList(limit = PAGE_SIZE, offset = 0)
    }

    private fun setupRecyclerView() {
        adapter = PokemonAdapter { pokemonId ->
            // Navegar a la pantalla de detalles
            val intent = Intent(this, PokemonDetailActivity::class.java)
            intent.putExtra("POKEMON_ID", pokemonId)
            startActivity(intent)
        }

        val layoutManager = LinearLayoutManager(this)
        rvPokemon.layoutManager = layoutManager
        rvPokemon.adapter = adapter

        // Scroll infinito
        rvPokemon.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                // Si llegamos al final y no estamos cargando
                if (!isLoading && (visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                    && firstVisibleItemPosition >= 0) {

                    // Cargar más Pokémon
                    val offset = adapter.getCurrentOffset()
                    viewModel.getPokemonList(limit = PAGE_SIZE, offset = offset)
                }
            }
        })
    }

    private fun observePokemonList() {
        lifecycleScope.launch {
            viewModel.pokemonListState.collect { state ->
                when (state) {
                    is PokemonListState.Loading -> {
                        isLoading = true
                        progressBar.visibility = View.VISIBLE
                    }
                    is PokemonListState.Success -> {
                        isLoading = false
                        progressBar.visibility = View.GONE
                        adapter.addPokemon(state.pokemonList.results)
                    }
                    is PokemonListState.Error -> {
                        isLoading = false
                        progressBar.visibility = View.GONE
                        Toast.makeText(this@MainActivity, state.message, Toast.LENGTH_SHORT).show()
                    }
                    else -> {}
                }
            }
        }
    }
}