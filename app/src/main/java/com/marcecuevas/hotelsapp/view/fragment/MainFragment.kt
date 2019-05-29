package com.marcecuevas.hotelsapp.view.fragment

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.marcecuevas.hotelsapp.R
import com.marcecuevas.hotelsapp.viewModel.HotelViewModel
import com.marcecuevas.hotelsapp.viewModel.HotelViewModelFactory
import kotlinx.android.synthetic.main.fragment_main.*
import org.kodein.di.android.x.closestKodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class MainFragment: GenericFragment(), KodeinAware {

    override val kodein by closestKodein()
    private val viewModelFactory: HotelViewModelFactory by instance()

    private lateinit var viewModel: HotelViewModel

    override fun layout(): Int = R.layout.fragment_main

    override fun init() {
        viewModel = ViewModelProviders.of(this,viewModelFactory).
            get(HotelViewModel::class.java)


        viewModel.hotels.observe(this, Observer {
            text.text = it.toString()
        })
    }


}