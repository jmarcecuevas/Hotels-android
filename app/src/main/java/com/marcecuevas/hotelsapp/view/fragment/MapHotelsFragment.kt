package com.marcecuevas.hotelsapp.view.fragment

import com.google.android.gms.maps.MapView
import com.marcecuevas.hotelsapp.R
import kotlinx.android.synthetic.main.fragment_map.*

class MapHotelsFragment: GenericMapFragment() {

    override val map: MapView
        get() = mapView

    override fun layout(): Int = R.layout.fragment_map

    override fun init() {
        super.init()
    }

}