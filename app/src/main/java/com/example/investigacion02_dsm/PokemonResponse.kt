package com.example.investigacion02_dsm

data class PokemonResponse(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val sprites: Sprites,
    val types: List<TypeSlot>,
    val stats: List<StatSlot>,
    val abilities: List<AbilitySlot>
)

// Sprites (imágenes del Pokémon)
data class Sprites(
    val front_default: String?,
    val front_shiny: String?,
    val back_default: String?,
    val other: Other?
)

data class Other(
    val official_artwork: OfficialArtwork?
)

data class OfficialArtwork(
    val front_default: String?
)

// Tipos del Pokémon
data class TypeSlot(
    val slot: Int,
    val type: Type
)

data class Type(
    val name: String,
    val url: String
)

// Estadísticas del Pokémon
data class StatSlot(
    val base_stat: Int,
    val effort: Int,
    val stat: Stat
)

data class Stat(
    val name: String,
    val url: String
)

// Habilidades del Pokémon
data class AbilitySlot(
    val is_hidden: Boolean,
    val slot: Int,
    val ability: Ability
)

data class Ability(
    val name: String,
    val url: String
)

// Para obtener lista de Pokémon
data class PokemonListResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonBasic>
)

data class PokemonBasic(
    val name: String,
    val url: String
)