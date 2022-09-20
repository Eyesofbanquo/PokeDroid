package com.example.hilttut.model

import com.squareup.moshi.Json

data class PokeStats(@field:Json(name="stats") val stats: List<PokeStat>)
data class PokeStatContainer(@field:Json(name="name") val name: String)
data class PokeStat(@field:Json(name="base_stat") val baseStat: String,
                    @field:Json(name="stat") val stat: PokeStatContainer)