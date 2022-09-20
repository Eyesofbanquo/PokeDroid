package com.example.hilttut.network

import com.example.hilttut.model.PokeStats
import com.example.hilttut.network.responses.AllPokemonResponse
import com.example.hilttut.network.responses.PokemonFormResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {
    @GET("/api/v2/pokemon")
    suspend fun getPokemon(): Response<AllPokemonResponse>
    @GET("/api/v2/pokemon-form/{id}")
    suspend fun getSpecificPokemonForm(@Path("id") id: String): Response<PokemonFormResponse>
    @GET("/api/v2/pokemon/{pokemon}")
    suspend fun getSpecificPokemon(@Path("pokemon") pokemon: String): Response<PokeStats>
}



