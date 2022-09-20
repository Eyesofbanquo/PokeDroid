package com.example.hilttut

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hilttut.model.Pokemon
import com.example.hilttut.network.PokemonRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val POKEMON_PARAM  = "pokemon"
private const val POKEMON_IMAGE_PARAM = "pokemonImageUrl"

@AndroidEntryPoint
class PokemonDetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var pokemon: Pokemon
    private lateinit var pokemonUrl: String
    private lateinit var pokemonStatsView: RecyclerView

    @Inject lateinit var pokeStatsAdapter: PokeStatsAdapter
    @Inject lateinit var pokemonRepository: PokemonRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            pokemon = it.get(POKEMON_PARAM) as Pokemon
            pokemonUrl = it.get(POKEMON_IMAGE_PARAM) as String
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

        pokemonStatsView.apply {
            val gridLayoutManager = GridLayoutManager(view.context, 2)
            gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (pokeStatsAdapter.getItemViewType(position)){
                    PokeStatsAdapter.HEADER -> 2
                    PokeStatsAdapter.ROW -> 1
                    else ->  1
                }
            }
        }
            this.layoutManager = gridLayoutManager
            this.adapter = pokeStatsAdapter
            this.isNestedScrollingEnabled = false
        }

        pokemonRepository.registerObserver(viewLifecycleOwner.lifecycle)
        pokemonRepository.getSpecificPokemon(pokemon).observe(viewLifecycleOwner) {
            pokeStatsAdapter.setPokeStats(stats=it,
                reloadData=true)
            pokeStatsAdapter.setPokeHeader(pokemonUrl)
        }
    }
}