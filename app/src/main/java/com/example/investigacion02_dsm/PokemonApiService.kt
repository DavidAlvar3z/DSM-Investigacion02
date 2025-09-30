package com.example.investigacion02_dsm

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApiService {

    // Obtener Pokémon por nombre o ID
    @GET("pokemon/{nameOrId}")
    suspend fun getPokemon(
        @Path("nameOrId") nameOrId: String
    ): Response<PokemonResponse>

    // Obtener lista de Pokémon (paginada)
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): Response<PokemonListResponse>
}