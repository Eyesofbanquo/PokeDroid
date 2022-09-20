package com.example.hilttut

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
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
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): PokeStatsView {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.info_view, parent, false)
        return PokeStatsView(layout)
    }

    override fun onBindViewHolder(holder: PokeStatsView,
                                  position: Int) {
        holder.title.text = "Hello"
        holder.subtitle.text = "Goodbye"
    }

    override fun getItemCount(): Int = 40

    @Provides
    fun makePokeStatsAdapter(): PokeStatsAdapter {
        return PokeStatsAdapter()
    }
}