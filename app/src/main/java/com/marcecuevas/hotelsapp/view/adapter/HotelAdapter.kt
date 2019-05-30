package com.marcecuevas.hotelsapp.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.marcecuevas.hotelsapp.R
import com.marcecuevas.hotelsapp.data.model.DTO.HotelItemDTO
import com.marcecuevas.hotelsapp.utils.FontVariable
import com.marcecuevas.hotelsapp.utils.fontVariable
import kotlinx.android.synthetic.main.item_hotel.view.*
import java.lang.StringBuilder

class HotelAdapter(val context: Context, val items: List<HotelItemDTO>): RecyclerView.Adapter<HotelAdapter.HotelViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.item_hotel,parent,false)
        return HotelViewHolder(context,view)
    }

    override fun onBindViewHolder(holder: HotelViewHolder, position: Int) {
        holder.bind(items.get(position))
    }

    override fun getItemCount(): Int {
        return items.size
    }


    class HotelViewHolder(context: Context, itemView: View): RecyclerView.ViewHolder(itemView){

        init {
            with(itemView){
                nameTextView.fontVariable(context,FontVariable.bold)
                nameTextView.textSize = 16f

                addressTextView.textSize = 14f

                ratingTextView.textSize = 14f

                priceTextView.textSize = 22f
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(hotel: HotelItemDTO){
            with(hotel){
                itemView.starsRatinBar.rating = stars.toFloat()
                itemView.nameTextView.text = name
                itemView.addressTextView.text = address
                itemView.ratingTextView.text = rating.toString()
                itemView.priceTextView.text = "${price.currency.mask} ${price.best}"

                Glide.with(itemView)
                    .load(mainPicture)
                    .placeholder(R.drawable.empty_state)
                    .error(R.drawable.empty_state)
                    .into(itemView.imageView)

                val amenitiesString = StringBuilder()
                for(amenity in amenities){
                    amenitiesString.append("* ${amenity.description}\n")
                }
                itemView.amenitiesTextView.text = amenitiesString.toString()
            }
        }
    }
}