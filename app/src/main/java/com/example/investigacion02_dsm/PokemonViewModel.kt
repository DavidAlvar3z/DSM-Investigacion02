package com.example.investigacion02_dsm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PokemonViewModel : ViewModel() {

    private val _pokemonState = MutableStateFlow<PokemonState>(PokemonState.Idle)
    val pokemonState: StateFlow<PokemonState> = _pokemonState

    private val _pokemonListState = MutableStateFlow<PokemonListState>(PokemonListState.Idle)
    val pokemonListState: StateFlow<PokemonListState> = _pokemonListState

    // Obtener un Pokémon específico por nombre o ID
    fun getPokemon(nameOrId: String) {
        viewModelScope.launch {
            try {
                _pokemonState.value = PokemonState.Loading

                val response = RetrofitInstance.api.getPokemon(nameOrId.lowercase())

                if (response.isSuccessful && response.body() != null) {
                    _pokemonState.value = PokemonState.Success(response.body()!!)
                } else {
                    _pokemonState.value = PokemonState.Error("Pokémon no encontrado")
                }
            } catch (e: Exception) {
                _pokemonState.value = PokemonState.Error("Error: ${e.message}")
            }
        }
    }

    // Obtener lista de Pokémon
    fun getPokemonList(limit: Int = 20, offset: Int = 0) {
        viewModelScope.launch {
            try {
                _pokemonListState.value = PokemonListState.Loading

                val response = RetrofitInstance.api.getPokemonList(limit, offset)

                if (response.isSuccessful && response.body() != null) {
                    _pokemonListState.value = PokemonListState.Success(response.body()!!)
                } else {
                    _pokemonListState.value = PokemonListState.Error("Error al obtener lista")
                }
            } catch (e: Exception) {
                _pokemonListState.value = PokemonListState.Error("Error: ${e.message}")
            }
        }
    }

    // Buscar Pokémon aleatorio (1-1010)
    fun getRandomPokemon() {
        val randomId = (1..1010).random()
        getPokemon(randomId.toString())
    }
}

// Estados para un Pokémon individual
sealed class PokemonState {
    object Idle : PokemonState()
    object Loading : PokemonState()
    data class Success(val pokemon: PokemonResponse) : PokemonState()
    data class Error(val message: String) : PokemonState()
}

// Estados para la lista de Pokémon
sealed class PokemonListState {
    object Idle : PokemonListState()
    object Loading : PokemonListState()
    data class Success(val pokemonList: PokemonListResponse) : PokemonListState()
    data class Error(val message: String) : PokemonListState()
}