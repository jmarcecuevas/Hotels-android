package com.marcecuevas.hotelsapp.view.fragment

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback

abstract class GenericMapFragment: GenericFragment(), OnMapReadyCallback {

    override fun layout(): Int = 0
    protected var googleMap: GoogleMap? = null
    abstract val map: MapView

    override fun init() {
        startMap()
    }

    private fun startMap(){
        map.onCreate(null)
        map.onResume()
        map.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap?) {
        this.googleMap = map
    }
}