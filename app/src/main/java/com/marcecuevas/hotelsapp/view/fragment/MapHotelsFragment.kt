package com.marcecuevas.hotelsapp.view.fragment

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.maps.MapView
import com.marcecuevas.hotelsapp.R
import com.marcecuevas.hotelsapp.data.model.DTO.HotelItemDTO
import com.marcecuevas.hotelsapp.data.model.entity.HotelEntity
import com.marcecuevas.hotelsapp.viewModel.HotelViewModel
import com.marcecuevas.hotelsapp.viewModel.HotelViewModelFactory
import kotlinx.android.synthetic.main.fragment_map.*
import org.kodein.di.generic.instance

class MapHotelsFragment: GenericMapFragment() {

    private val viewModelFactory: HotelViewModelFactory by instance()
    private lateinit var viewModel: HotelViewModel

    override val map: MapView
        get() = mapView


    override fun layout(): Int = R.layout.fragment_map

    override fun init() {
        super.init()

        viewModel = ViewModelProviders.of(this,viewModelFactory).
            get(HotelViewModel::class.java)

        viewModel.allViewed.observe(this, Observer {
            drawMarkers(it)
        })
    }

    private fun drawMarkers(items: List<HotelEntity>?){
        googleMap?.clear()
        items?.let {
            for (item in it) {
                drawMarker(item.lat,item.lng)
            }
        }
        with(items?.last()){
            moveCamera(this?.lat, this?.lng)
        }
    }
}