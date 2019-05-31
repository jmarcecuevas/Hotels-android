package com.marcecuevas.hotelsapp.view.fragment

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.marcecuevas.hotelsapp.R
import com.marcecuevas.hotelsapp.view.adapter.FavouriteAdapter
import com.marcecuevas.hotelsapp.viewModel.HotelViewModel
import com.marcecuevas.hotelsapp.viewModel.HotelViewModelFactory
import kotlinx.android.synthetic.main.fragment_favourite.*
import org.kodein.di.generic.instance

class ViewedHotelsFragment: GenericFragment() {

    private val viewModelFactory: HotelViewModelFactory by instance()

    private lateinit var viewModel: HotelViewModel
    private lateinit var adapter: FavouriteAdapter

    override fun init() {

        viewModel = ViewModelProviders.of(this,viewModelFactory).
            get(HotelViewModel::class.java)

        adapter = FavouriteAdapter(context,{
            navigateToEventDetails(it?.id)
        })

        hotelsRecyclerView.layoutManager = LinearLayoutManager(context)
        hotelsRecyclerView.adapter = adapter

        viewModel.allViewed.observe(this, Observer {
            adapter.loadItems(it)
        })
    }

    private fun navigateToEventDetails(hotelID: String?){
        val directions = ViewedHotelsFragmentDirections.viewedFragment(hotelID)
        findNavController().navigate(directions)
    }

    override fun layout(): Int {
        return R.layout.fragment_favourite
    }

}