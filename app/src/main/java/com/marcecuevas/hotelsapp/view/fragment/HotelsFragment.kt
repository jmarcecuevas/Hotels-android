package com.marcecuevas.hotelsapp.view.fragment

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.marcecuevas.hotelsapp.R
import com.marcecuevas.hotelsapp.utils.FontVariable
import com.marcecuevas.hotelsapp.utils.fontVariable
import com.marcecuevas.hotelsapp.view.adapter.HotelAdapter
import com.marcecuevas.hotelsapp.view.adapter.SliderViewPager
import com.marcecuevas.hotelsapp.viewModel.HotelViewModel
import com.marcecuevas.hotelsapp.viewModel.HotelViewModelFactory
import kotlinx.android.synthetic.main.fragment_main.*
import org.kodein.di.android.x.closestKodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class HotelsFragment: GenericFragment(), KodeinAware {

    override val kodein by closestKodein()
    private val viewModelFactory: HotelViewModelFactory by instance()

    private lateinit var viewModel: HotelViewModel
    private lateinit var adapter: HotelAdapter

    override fun layout(): Int = R.layout.fragment_main

    override fun init() {

        setupTextViews()

        setupViewPager()

        context?.let {
            adapter = HotelAdapter(it,{
                navigateToEventDetails(it.id)
            })
            hotelsRecyclerView.layoutManager = LinearLayoutManager(context)
            hotelsRecyclerView.adapter = adapter
        }

        viewModel = ViewModelProviders.of(this,viewModelFactory).
            get(HotelViewModel::class.java)

        viewModel.hotels.observe(this, Observer {
            adapter.loadItems(it.items)
        })
    }

    private fun setupTextViews(){
        context?.let {
            firstHeaderTV.fontVariable(it,FontVariable.bold)
            secondHeaderTV.fontVariable(it,FontVariable.light)
            thirdHeaderTV.fontVariable(it,FontVariable.bold)
        }
    }

    private fun setupViewPager(){
        context?.let {
            viewPager.adapter = SliderViewPager(it)
            pageIndicator.attachToViewPager(viewPager)
        }
    }

    private fun navigateToEventDetails(hotelID: String){
        val directions = HotelsFragmentDirections.hotelDetailFragment(hotelID)
        findNavController().navigate(directions)
    }
}