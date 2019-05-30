package com.marcecuevas.hotelsapp.view.fragment

import android.util.Log
import com.marcecuevas.hotelsapp.R

class FavouriteFragment: GenericFragment() {

    override fun init() {
        Log.e("Init","FavouriteFragment")
    }

    override fun layout(): Int {
        return R.layout.fragment_favourite
    }

}