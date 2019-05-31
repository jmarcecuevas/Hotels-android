package com.marcecuevas.hotelsapp.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marcecuevas.hotelsapp.R
import com.marcecuevas.hotelsapp.data.model.DTO.HotelDTO


class FavouriteAdapter(val context: Context?): RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder>() {

    var items: List<HotelDTO>? = emptyList()

    fun loadItems(newItems: List<HotelDTO>?){
        items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_favorite,parent,false)
        return FavouriteViewHolder(context, view)
    }

    override fun getItemCount(): Int {
        return items?.count() ?: 0
    }

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        holder.bind(items?.get(position))
    }

    class FavouriteViewHolder(context: Context?, itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {

        }

        @SuppressLint("SetTextI18n")
        fun bind(item: HotelDTO?){

        }
    }
}