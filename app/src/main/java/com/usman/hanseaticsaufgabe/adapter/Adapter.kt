package com.usman.hanseaticsaufgabe.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.usman.hanseaticsaufgabe.model.MovieResponseItem
import com.usman.hanseaticsaufgabe.R
import kotlinx.android.synthetic.main.item_simple_grid.view.*

class Adapter(private val list: List<MovieResponseItem>) : RecyclerView.Adapter<Adapter.ImagesViewHolder>() {

    inner class ImagesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ImagesViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_staggered_grid,
            parent,
            false
        )
    )
    override fun getItemCount() =  list.size

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        val item = list[position]
        holder.itemView.apply {
            ivImage.load(item.img)
        }
    }
}