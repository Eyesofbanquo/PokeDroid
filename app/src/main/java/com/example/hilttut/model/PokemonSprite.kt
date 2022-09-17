package com.example.hilttut.model

import com.squareup.moshi.Json

data class PokemonSprite(@field:Json(name="front_default") val default: String)