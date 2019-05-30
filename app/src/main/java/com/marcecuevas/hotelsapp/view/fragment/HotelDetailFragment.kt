package com.marcecuevas.hotelsapp.view.fragment

import android.util.Log
import com.marcecuevas.hotelsapp.R

class HotelDetailFragment: GenericFragment() {

    override fun layout(): Int {
        return R.layout.fragment_hotel_detail
    }

    override fun init() {
        Log.e("Init","HotelDetail")
    }

}