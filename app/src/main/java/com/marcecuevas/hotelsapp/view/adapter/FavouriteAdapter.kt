package com.marcecuevas.hotelsapp.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.marcecuevas.hotelsapp.R
import com.marcecuevas.hotelsapp.data.model.DTO.HotelDTO
import com.marcecuevas.hotelsapp.data.model.entity.HotelEntity
import com.marcecuevas.hotelsapp.utils.bold
import com.marcecuevas.hotelsapp.utils.light
import kotlinx.android.synthetic.main.item_favorite.view.*


class FavouriteAdapter(val context: Context?, val onClick: (HotelEntity?) -> Unit): RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder>() {

    var items: List<HotelEntity>? = emptyList()

    fun loadItems(newItems: List<HotelEntity>?){
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
        holder.itemView.setOnClickListener{
            onClick(items?.get(position))
        }
    }

    class FavouriteViewHolder(context: Context?, itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.nameTextView.bold(context)
            itemView.addressTextView.light(context)
            itemView.priceTextView.bold(context)
        }

        @SuppressLint("SetTextI18n")
        fun bind(item: HotelEntity?){
            Glide.with(itemView).load(item?.imageURL).into(itemView.imageView)

            itemView.nameTextView.text = item?.name
            itemView.starsRatingBar.numStars = 5
            item?.stars?.let {
                itemView.starsRatingBar.rating = it.toFloat()
            }
            itemView.addressTextView.text = item?.address
            itemView.priceTextView.text = "${item?.currencyMask} ${item?.price?.toString()}"
        }
    }
}