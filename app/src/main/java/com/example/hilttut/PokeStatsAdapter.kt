package com.example.hilttut

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.hilttut.model.PokeStat
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent
import javax.inject.Qualifier

class PokeDetailView(private val view: View): RecyclerView.ViewHolder(view) {
    fun bindHeader(pokemonImageUrl: String) {
        if (pokemonImageUrl.isNullOrEmpty()) return
        val imageView: ImageView = view.findViewById(R.id.pokemonHeaderImageView)
        imageView.load(pokemonImageUrl) {
            crossfade(true)
            placeholder(R.drawable.ic_launcher_background)
        }
    }
    fun bindContent(titleString: String, subtitleString: String) {
        val title: TextView = view.findViewById(R.id.infoViewTitle)
        val subtitle: TextView = view.findViewById(R.id.infoViewSubtitle)
        title.text = titleString
        subtitle.text = subtitleString

    }
}

@Module
@InstallIn(FragmentComponent::class)
class PokeStatsAdapter: RecyclerView.Adapter<PokeDetailView>() {

    companion object {
        const val HEADER = 0
        const val ROW = 1
    }

    private var _data: List<PokeStat> = listOf()
    private var headerUrl: String = ""

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): PokeDetailView {
        val layoutToInflate = when (viewType) {
            HEADER -> R.layout.pokemon_detail_header
            else -> R.layout.info_view
        }
        val layout = LayoutInflater.from(parent.context)
            .inflate(layoutToInflate, parent, false)
        return PokeDetailView(layout)
    }

    override fun onBindViewHolder(holder: PokeDetailView,
                                  position: Int) {
        if (position == HEADER) {
            holder.bindHeader(headerUrl)
        } else {
            val stat = _data[position-ROW]
            holder.bindContent(stat.stat.name, stat.baseStat)
        }
    }

    override fun getItemCount(): Int = _data.count() + 1

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return HEADER
        }
        return ROW
    }

    /**
     * Set the stat data for this adapter.
     *
     * @param stats The data to replace the adapter data. Pass in empty params to reset data.
     * @param reloadData Pass in true to reload recycler list.
     */
    fun setPokeStats(stats: List<PokeStat> = listOf(),
                     reloadData: Boolean = false) {
        _data = stats
        if (reloadData) {
            notifyDataSetChanged()
        }
    }

    fun setPokeHeader(headerUrlString: String) {
        headerUrl = headerUrlString
        notifyDataSetChanged()
    }

    @Provides
    fun makePokeStatsAdapter(): PokeStatsAdapter {
        return PokeStatsAdapter()
    }
}