package com.marcecuevas.hotelsapp.view.activity

import com.marcecuevas.hotelsapp.R
import com.marcecuevas.hotelsapp.view.fragment.MainFragment

class MainActivity : GenericActivity() {

    override fun layout(): Int = R.layout.activity_main

    override fun fragmentLayoutID(): Int = R.id.fragmentContainer

    override fun init() {
        addFragmentWithBackStack(MainFragment::class.java,false)
    }
}
