package com.example.hilttut

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hilttut.model.PokeStat
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent
import javax.inject.Qualifier

class PokeStatsView(private val view: View): RecyclerView.ViewHolder(view) {
    val title: TextView = view.findViewById(R.id.title)
    val subtitle: TextView = view.findViewById(R.id.subtitle)
}

@Module
@InstallIn(FragmentComponent::class)
class PokeStatsAdapter: RecyclerView.Adapter<PokeStatsView>() {

    private var _data: List<PokeStat> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): PokeStatsView {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.info_view, parent, false)
        return PokeStatsView(layout)
    }

    override fun onBindViewHolder(holder: PokeStatsView,
                                  position: Int) {
        val stat = _data[position]
        holder.title.text = stat.stat.name
        holder.subtitle.text = stat.baseStat
    }

    override fun getItemCount(): Int = _data.count()

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