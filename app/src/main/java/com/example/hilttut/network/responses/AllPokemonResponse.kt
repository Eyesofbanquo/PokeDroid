package com.example.hilttut.network.responses

import com.example.hilttut.model.Pokemon
import com.squareup.moshi.Json

data class AllPokemonResponse(
    @field:Json(name="count")
    val count: Int,
    @field:Json(name="results")
    val results: List<Pokemon>
)