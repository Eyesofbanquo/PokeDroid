package com.example.hilttut

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hilttut.model.Pokemon
import com.example.hilttut.network.PokemonRepository
import com.example.hilttut.network.PokemonService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val POKEMON_PARAM  = "pokemon"

@AndroidEntryPoint
class PokemonDetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var pokemon: Pokemon
    private lateinit var pokemonStatsView: RecyclerView

    @Inject lateinit var pokeStatsAdapter: PokeStatsAdapter
    @Inject lateinit var pokemonRepository: PokemonRepository

    init {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            pokemon = it.get(POKEMON_PARAM) as Pokemon
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon_detail,
            container,
            false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pokemonStatsView = view.findViewById(R.id.pokemonStatsView)
        pokemonStatsView.layoutManager = GridLayoutManager(view.context, 2)
        pokemonStatsView.adapter = pokeStatsAdapter
        pokemonRepository.registerObserver(viewLifecycleOwner.lifecycle)
        pokemonRepository.getSpecificPokemon(pokemon).observe(viewLifecycleOwner) {
            Log.i("SPECIFIC", it.toString())
        }
    }
}