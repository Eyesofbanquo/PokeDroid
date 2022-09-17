package com.example.hilttut.network

import android.util.Log
import androidx.lifecycle.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.*
import javax.inject.Inject
import javax.inject.Qualifier
import kotlin.coroutines.CoroutineContext

interface Repository: DefaultLifecycleObserver {
    fun getAllPokemon(): LiveData<List<PokemonListResponse>>
    fun registerObserver(lifecycle: Lifecycle)
}

@Qualifier
annotation class PokemonRepositoryQualfier

@Module
@InstallIn(ActivityComponent::class)
abstract class RepositoryModule {
    @PokemonRepositoryQualfier
    @Binds
    abstract fun makePokemonRepository(pokemonRepository: PokemonRepository): Repository
}

@ActivityScoped
class PokemonRepository @Inject constructor(
    var pokemonService: PokemonService
) : Repository, CoroutineScope {

    // region Properties
    private var pokemon = MutableLiveData<List<PokemonListResponse>>()
    private val job: Job = Job()
    // endregion

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
    }

    private suspend fun fetchPokemonList() {
        val allPokemon = withContext(Dispatchers.IO) {
            val result = pokemonService.getPokemon()
            result.body()?.results ?: listOf()
        }

        val regex = Regex("(\\/\\w+\\/)\$")
        val regexNumber = Regex("\\w+")

        /* Get Pokemon indices */
        val listOfIndices = allPokemon.map {
            val indexMatchString = regex.find(it.url)?.value ?: ""
            val index = regexNumber.find(indexMatchString)?.value ?: ""
            index
        }

        Log.i("Tags", listOfIndices.toString())

        val pokemons = withContext(Dispatchers.IO) {
            var pokemonResponse: MutableList<PokemonListResponse> = mutableListOf()
            for (index in listOfIndices) {
                val result = pokemonService.getSpecificPokemon(index)
                val formResult = pokemonService.getSpecificPokemonForm(index)

                val specificPokemonResult = result.body()?.form
                val specificPokemonFormResult = formResult.body()
                val name = specificPokemonFormResult?.pokemon?.name ?: ""
                val sprite = specificPokemonFormResult?.sprites?.default ?: ""
                Log.i("Named", specificPokemonFormResult.toString())
                val returnedPokemon = PokemonListResponse(name, sprite)
                pokemonResponse.add(returnedPokemon)
            }
            Log.i("Pokemons", "Returned")
            pokemonResponse
        }


        Log.i("Index", listOfIndices.toString())

        pokemon.value = pokemons
    }

    private suspend fun fetchAllIndividualPokemon(index: String) {

    }

    private fun cancelJob() {
        if (job.isActive) {
            job.cancel()
        }
    }

    // region Overrides

    override fun getAllPokemon(): LiveData<List<PokemonListResponse>> {
        launch { fetchPokemonList() }
        return pokemon
    }

    override fun registerObserver(lifecycle: Lifecycle){
        lifecycle.addObserver(this)
    }

    // endregion

}