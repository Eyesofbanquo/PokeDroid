package com.example.hilttut.network.responses

import com.example.hilttut.model.Pokemon
import com.example.hilttut.model.PokemonSprite
import com.squareup.moshi.Json

data class PokemonFormResponse(@field:Json(name="pokemon") val pokemon: Pokemon,
                               @field:Json(name="sprites") val sprites: PokemonSprite
)