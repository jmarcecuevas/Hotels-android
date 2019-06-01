package com.marcecuevas.hotelsapp.view.fragment

import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.marcecuevas.hotelsapp.R
import com.marcecuevas.hotelsapp.data.model.DTO.HotelDTO
import com.marcecuevas.hotelsapp.data.model.DTO.HotelItemDTO
import com.marcecuevas.hotelsapp.data.model.Error
import com.marcecuevas.hotelsapp.data.model.entity.HotelEntity
import com.marcecuevas.hotelsapp.utils.FontVariable
import com.marcecuevas.hotelsapp.utils.bold
import com.marcecuevas.hotelsapp.utils.fontVariable
import com.marcecuevas.hotelsapp.utils.light
import com.marcecuevas.hotelsapp.view.adapter.HotelAdapter
import com.marcecuevas.hotelsapp.view.adapter.SliderViewPager
import com.marcecuevas.hotelsapp.viewModel.HotelViewModel
import com.marcecuevas.hotelsapp.viewModel.HotelViewModelFactory
import kotlinx.android.synthetic.main.fragment_main.*
import org.kodein.di.android.x.closestKodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class HotelsFragment: GenericFragment() {

    private val viewModelFactory: HotelViewModelFactory by instance()
    private lateinit var viewModel: HotelViewModel
    private lateinit var adapter: HotelAdapter

    private lateinit var hotels: HotelDTO

    override fun layout(): Int = R.layout.fragment_main

    override fun init() {

        setupTextViews()

        setupViewPager()

        context?.let {
            adapter = HotelAdapter(it,{
                navigateToEventDetails(it?.id)
            })
            hotelsRecyclerView.layoutManager = LinearLayoutManager(context)
            hotelsRecyclerView.adapter = adapter
        }

        viewModel = ViewModelProviders.of(this,viewModelFactory).
            get(HotelViewModel::class.java)

        viewModel.hotelsLivedata.observe(this, Observer {
            this.hotels = it
            adapter.loadItems(it.items)
        })

        viewModel.errorLiveData.observe(this, Observer {
            showError(com.marcecuevas.hotelsapp.data.model.Error(getString(R.string.error),it))
        })
    }

    private fun setupTextViews(){
        firstHeaderTV.bold(context)
        secondHeaderTV.light(context)
        thirdHeaderTV.bold(context)
    }

    private fun setupViewPager(){
        context?.let {
            viewPager.adapter = SliderViewPager(it)
            pageIndicator.attachToViewPager(viewPager)
        }
    }

    private fun navigateToEventDetails(hotelID: String?){
        val directions = HotelsFragmentDirections.hotelDetailFragment(hotelID)
        findNavController().navigate(directions)
    }
}