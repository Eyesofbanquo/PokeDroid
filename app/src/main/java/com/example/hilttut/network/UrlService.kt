package com.example.hilttut.network

import com.example.hilttut.network.responses.AllPokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface UrlService {
    @GET
    suspend fun loadUrl(@Url urlString: String): Response<AllPokemonResponse>
}