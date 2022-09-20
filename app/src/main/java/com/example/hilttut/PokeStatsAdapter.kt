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
    fun bindHeader() {

    }
    fun bindContent(titleString: String, subtitleString: String) {
        val title: TextView = view.findViewById(R.id.title)
        val subtitle: TextView = view.findViewById(R.id.subtitle)
        title.text = titleString
        subtitle.text = subtitleString

    }
}

@Module
@InstallIn(FragmentComponent::class)
class PokeStatsAdapter: RecyclerView.Adapter<PokeDetailView>() {

    enum class ViewType(val value: Int) {
        HEADER(0),
        CONTENT(1)
    }

    private var _data: List<PokeStat> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): PokeDetailView {
        val layoutToInflate = when (viewType) {
            0 -> R.layout.pokemon_detail_header
            else -> R.layout.info_view
        }
        val layout = LayoutInflater.from(parent.context)
            .inflate(layoutToInflate, parent, false)
        return PokeDetailView(layout)
    }

    override fun onBindViewHolder(holder: PokeDetailView,
                                  position: Int) {
        if (position == 0) {

        } else {
            val stat = _data[position-1]
            holder.bindContent(stat.stat.name, stat.baseStat)
        }
//        holder.title.text = stat.stat.name
//        holder.subtitle.text = stat.baseStat
    }

    override fun getItemCount(): Int = _data.count() + 1

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return 0
        }
        return 1
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

    @Provides
    fun makePokeStatsAdapter(): PokeStatsAdapter {
        return PokeStatsAdapter()
    }
}