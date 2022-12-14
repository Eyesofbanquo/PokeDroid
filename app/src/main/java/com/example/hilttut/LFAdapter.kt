package com.example.hilttut

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.hilttut.model.Pokemon

class LFViewHolder(val view: View): RecyclerView.ViewHolder(view) {
    val textView: TextView = view.findViewById(R.id.itemTextView)
    val imageView: ImageView = view.findViewById(R.id.pokemonImageView)
}

class LFAdapter: RecyclerView.Adapter<LFViewHolder>() {

    private var _data: List<Pokemon> = listOf()

    fun setPokemon(newPokemon: List<Pokemon>){
        _data = newPokemon
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): LFViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view, parent, false)
        return LFViewHolder(layout)
    }

    override fun onBindViewHolder(holder: LFViewHolder,
                                  position: Int) {
        val pokemon = _data[position]
        val pokemonImageUrl = pokemon.url

        val textView = holder.textView
        val imageView = holder.imageView
        imageView.load(pokemonImageUrl) {
            crossfade(true)
            placeholder(R.drawable.ic_launcher_background)
        }
        textView.text = holder.itemView.context.getString(R.string.pokemonName, pokemon.name)
        holder.itemView.setOnClickListener {
            val action = ListFragmentDirections
                .actionListFragment2ToPokemonDetailFragment(pokemon=pokemon,
                    label=pokemon.name.uppercase(),
                    pokemonImageUrl=pokemonImageUrl)
            it.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int = _data.count()


}