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
import com.marcecuevas.hotelsapp.data.model.DTO.HotelItemDTO
import com.marcecuevas.hotelsapp.utils.FontVariable
import com.marcecuevas.hotelsapp.utils.bold
import com.marcecuevas.hotelsapp.utils.fontVariable
import kotlinx.android.synthetic.main.item_hotel.view.*
import java.lang.StringBuilder

class HotelAdapter(val context: Context?, val onClick: (HotelItemDTO?) -> Unit): RecyclerView.Adapter<HotelAdapter.HotelViewHolder>() {

    var items: List<HotelItemDTO>? = emptyList()

    fun loadItems(newItems: List<HotelItemDTO>?){
        items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.item_hotel,parent,false)
        return HotelViewHolder(context,view)
    }

    override fun onBindViewHolder(holder: HotelViewHolder, position: Int) {
        holder.bind(items?.get(position))
        holder.itemView.setOnClickListener{
            onClick(items?.get(position))
        }
    }

    override fun getItemCount(): Int {
        return items?.size ?:0
    }

    class HotelViewHolder(context: Context?, itemView: View): RecyclerView.ViewHolder(itemView){

        init {
            with(itemView){
                nameTextView.bold(context)
                nameTextView.textSize = 16f

                addressTextView.textSize = 14f

                ratingTextView.textSize = 14f
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(hotel: HotelItemDTO?){
            with(hotel){
                this?.stars?.let {
                    itemView.starsRatinBar.rating = it.toFloat()
                }

                itemView.nameTextView.text = this?.name
                itemView.addressTextView.text = this?.address
                itemView.ratingTextView.text = this?.rating.toString()
                itemView.priceTextView.text = "${this?.price?.currency?.mask} ${this?.price?.best}"

                Glide.with(itemView)
                    .load(this?.mainPicture)
                    .placeholder(R.drawable.empty_state)
                    .error(R.drawable.empty_state)
                    .into(itemView.imageView)

                val amenitiesString = StringBuilder()
                this?.amenities?.let {
                    for(amenity in it){
                        amenitiesString.append("* ${amenity.description}\n")
                    }
                }
                itemView.amenitiesTextView.text = amenitiesString.toString()
            }
        }
    }
}