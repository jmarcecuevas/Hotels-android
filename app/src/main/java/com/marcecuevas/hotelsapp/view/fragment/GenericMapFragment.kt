package com.marcecuevas.hotelsapp.view.fragment

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.marcecuevas.hotelsapp.utils.ifLet

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

    fun drawMarker(lat: String?, lng: String?){
        ifLet(lat,lng){
                (lat,lng) ->
            googleMap?.mapType = GoogleMap.MAP_TYPE_NORMAL
            googleMap?.addMarker(MarkerOptions()
                .position(LatLng(lat.toDouble(),lng.toDouble())))
        }
    }

    fun moveCamera(lat: String?, lng: String?){
        ifLet(lat,lng){
            (lat,lng)->val cameraposition = CameraPosition
                .builder()
                .target(LatLng(lat.toDouble(),lng.toDouble()))
                .zoom(12f)
                .bearing(0f)
                .build()
            googleMap?.moveCamera(CameraUpdateFactory.newCameraPosition(cameraposition))
        }

    }
}