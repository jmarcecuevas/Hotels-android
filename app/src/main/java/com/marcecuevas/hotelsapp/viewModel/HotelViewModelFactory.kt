package com.marcecuevas.hotelsapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.marcecuevas.hotelsapp.data.repository.HotelRepository

class HotelViewModelFactory(
    private val hotelRepository: HotelRepository
): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun<T: ViewModel?> create(modelClass: Class<T>): T{
        return HotelViewModel(hotelRepository) as T
    }
}