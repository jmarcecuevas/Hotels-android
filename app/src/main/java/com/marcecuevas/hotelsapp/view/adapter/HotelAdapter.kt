package com.marcecuevas.hotelsapp.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.marcecuevas.hotelsapp.R
import com.marcecuevas.hotelsapp.data.model.DTO.HotelItemDTO
import com.marcecuevas.hotelsapp.utils.fontVariable
import kotlinx.android.synthetic.main.item_hotel.view.*

class HotelAdapter(val context: Context, val items: List<HotelItemDTO>): RecyclerView.Adapter<HotelAdapter.HotelViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_hotel,parent,false)
        return HotelViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: HotelViewHolder, position: Int) {
        holder.bind(items.get(position))
    }


    class HotelViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bind(hotel: HotelItemDTO){
            with(hotel){
                itemView.starsRatinBar.numStars = stars
                itemView.nameTextView.text = name
                itemView.addressTextView.text = address
                itemView.ratingTextView.text = rating.toString()
                itemView.priceTextView.text = "$$price"
                Glide.with(itemView)
                    .load(mainPicture)
                    .into(itemView.imageView)
            }
        }
    }
}