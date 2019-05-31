package com.marcecuevas.hotelsapp.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marcecuevas.hotelsapp.R
import com.marcecuevas.hotelsapp.data.model.DTO.AmenityDTO
import com.marcecuevas.hotelsapp.utils.FontVariable
import com.marcecuevas.hotelsapp.utils.fontVariable
import kotlinx.android.synthetic.main.item_amenity.view.*

class AmenitiesAdapter(val context: Context): RecyclerView.Adapter<AmenitiesAdapter.AmenityViewHolder>() {

    var items: List<AmenityDTO> = emptyList()

    fun loadItems(newItems: List<AmenityDTO>){
        items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AmenityViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.item_amenity,parent,false)
        return AmenityViewHolder(context,view)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: AmenityViewHolder, position: Int) {
        holder.bind(items.get(position))
    }

    class AmenityViewHolder(context: Context, itemView: View): RecyclerView.ViewHolder(itemView){

        val mapImages: Map<String,Int> = mapOf<String,Int>(
            "WIFI" to R.drawable.ic_wifi,
            "PISCN" to R.drawable.ic_swimming,
            "BREAKFAST" to R.drawable.ic_coffee,
            "PARKING" to R.drawable.ic_parking)

        fun bind(item: AmenityDTO){
            with(itemView){
                amenityName.text = item.description

                mapImages.get(item.id)?.let {
                    amenityName.setCompoundDrawablesRelativeWithIntrinsicBounds(it,0,0,0)
                    //amenityName.setCompoundDrawables(resources.getDrawable(R.drawable.ic_place),null,null,null)
                }
            }
        }

    }
}