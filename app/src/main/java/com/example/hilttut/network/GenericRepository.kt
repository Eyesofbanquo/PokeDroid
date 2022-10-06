package com.example.hilttut.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hilttut.model.Pokemon
import com.example.hilttut.network.repositories.GenericApiRepository
import com.example.hilttut.network.responses.AllPokemonResponse
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@ActivityScoped
class GenericRepository @Inject constructor(
    var genericService: UrlService
): GenericApiRepository, CoroutineScope {
    private val job: Job = Job()
    private val response = MutableLiveData<AllPokemonResponse?>()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun loadAllPokemon(urlString: String): LiveData<AllPokemonResponse?> {
        val pokemonJob = launch { allPokemon(urlString) }
        return response
    }

    private suspend fun allPokemon(urlString: String) {
        var pokemon = listOf<Pokemon>()
        val allResponse = withContext(Dispatchers.IO) {
            val allPokemonResponse = genericService
                .loadAllPokemonUrl("https://pokeapi.co/api/v2/pokemon")
            allPokemonResponse
        }
        response.value = allResponse.body()
    }


}