package com.marcecuevas.hotelsapp.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marcecuevas.hotelsapp.R
import com.marcecuevas.hotelsapp.data.model.DTO.AmenityDTO
import com.marcecuevas.hotelsapp.data.model.DTO.HotelItemDTO
import com.marcecuevas.hotelsapp.utils.bold
import kotlinx.android.synthetic.main.item_hotel.view.*


class HotelAdapter(context: Context?, val onClick: (HotelItemDTO?) -> Unit) : GenericRecyclerAdapter<HotelAdapter.ViewHolder, HotelItemDTO>(context) {

    override fun getHolder(parent: ViewGroup): ViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.item_hotel,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: HotelAdapter.ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.itemView.setOnClickListener(){
            onClick(items?.get(position))
        }
    }

    class ViewHolder(itemView: View): GenericRecyclerAdapter.GenericViewHolder<HotelItemDTO>(itemView) {

        init {
            with(itemView){
                nameTextView.bold(context)
                nameTextView.textSize = 16f
                addressTextView.textSize = 14f
                ratingTextView.textSize = 14f
            }
        }

        @SuppressLint("SetTextI18n")
        override fun bind(item: HotelItemDTO?) {
            with(item){
                this?.stars?.let {
                    itemView.starsRatinBar.rating = it.toFloat()
                }

                itemView.nameTextView.text = this?.name
                itemView.addressTextView.text = this?.address
                itemView.ratingTextView.text = this?.rating.toString()
                itemView.priceTextView.text = "${this?.price?.currency?.mask} ${this?.price?.best}"

                loadImage(itemView,this?.mainPicture)
                setupAmenities(this?.amenities)
            }
        }

        private fun setupAmenities(amenities: List<AmenityDTO>?){
            val amenitiesString = java.lang.StringBuilder()
            amenities?.let {
                for(amenity in it){
                    amenitiesString.append("* ${amenity.description}\n")
                }
            }
            itemView.amenitiesTextView.text = amenitiesString.toString()
        }

        private fun loadImage(itemView:View, mainPicture: String?){
            com.bumptech.glide.Glide.with(itemView)
                .load(mainPicture)
                .placeholder(com.marcecuevas.hotelsapp.R.drawable.empty_state)
                .error(com.marcecuevas.hotelsapp.R.drawable.empty_state)
                .into(itemView.imageView)
        }
    }
}