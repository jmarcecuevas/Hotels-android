package com.marcecuevas.hotelsapp.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marcecuevas.hotelsapp.R
import com.marcecuevas.hotelsapp.data.model.DTO.AmenityDTO
import kotlinx.android.synthetic.main.item_amenity.view.*

class AmenitiesAdapter(context: Context?): GenericRecyclerAdapter<AmenitiesAdapter.AmenityViewHolder,AmenityDTO>(context) {

    override fun getHolder(parent: ViewGroup): AmenityViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.item_amenity,parent,false)
        return AmenityViewHolder(view)
    }

    class AmenityViewHolder(itemView: View): GenericRecyclerAdapter.GenericViewHolder<AmenityDTO>(itemView){

        val mapImages: Map<String,Int> = mapOf<String,Int>(
            "WIFI" to R.drawable.ic_wifi,
            "PISCN" to R.drawable.ic_swimming,
            "BREAKFST" to R.drawable.ic_coffee,
            "PARKING" to R.drawable.ic_parking)

        override fun bind(item: AmenityDTO?){
            with(itemView){
                item?.let {
                    amenityName.text = it.description

                    mapImages.get(it.id)?.let {
                        amenityName.setCompoundDrawablesRelativeWithIntrinsicBounds(it,0,0,0)
                    }
                }
            }
        }

    }
}