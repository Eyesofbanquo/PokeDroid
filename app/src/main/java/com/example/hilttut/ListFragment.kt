package com.example.hilttut

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hilttut.injection_modules.PokemonRepositoryQualifier
import com.example.hilttut.model.Person
import com.example.hilttut.model.SpanishQualiifier
import com.example.hilttut.network.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class ListFragment : Fragment() {

    @SpanishQualiifier
    @Inject
    lateinit var spanishPerson: Person

    @Inject
    lateinit var pokemonService: PokemonService

    @PokemonRepositoryQualifier
    @Inject
    lateinit var pokemonRepository: PokeApiRepository

    private lateinit var recyclerView:RecyclerView
    private val recyclerViewAdapter: LFAdapter = LFAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list,
            container,
            false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById<RecyclerView>(R.id.reyclerView)
        recyclerView.addItemDecoration(DividerItemDecoration(context,
            LinearLayoutManager.VERTICAL))
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = recyclerViewAdapter

        pokemonRepository.registerObserver(viewLifecycleOwner.lifecycle)
        pokemonRepository.getAllPokemon().observe(viewLifecycleOwner) {
            recyclerViewAdapter.setPokemon(it)
            recyclerViewAdapter.notifyDataSetChanged()
        }
    }
}