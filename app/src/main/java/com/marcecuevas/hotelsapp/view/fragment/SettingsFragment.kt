package com.marcecuevas.hotelsapp.view.fragment

import android.util.Log
import com.marcecuevas.hotelsapp.R

class SettingsFragment: GenericFragment() {

    override fun layout(): Int {
        return R.layout.fragment_settings
    }

    override fun init() {
       Log.e("Init","SettingsFragment")
    }

}