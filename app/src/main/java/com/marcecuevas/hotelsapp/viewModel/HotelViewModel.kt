package com.marcecuevas.hotelsapp.viewModel

import androidx.lifecycle.ViewModel
import com.marcecuevas.hotelsapp.data.repository.HotelRepository
import com.marcecuevas.hotelsapp.utils.lazyDeferred

class HotelViewModel(val repository: HotelRepository): ViewModel(){

    val hotels by lazyDeferred() {
        repository.getHotels()
    }
}