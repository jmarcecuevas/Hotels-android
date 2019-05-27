package com.marcecuevas.hotelsapp.view.fragment

import com.marcecuevas.hotelsapp.R
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment: GenericFragment() {

    override fun layout(): Int = R.layout.fragment_main

    override fun init() {
        text.text = "Marce"
    }


}