package com.marcecuevas.hotelsapp.data.repository

import androidx.lifecycle.LiveData
import com.marcecuevas.hotelsapp.data.model.DTO.HotelDTO
import com.marcecuevas.hotelsapp.data.network.HotelNetworkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class HotelRepositoryImpl(private val hotelNetworkDataSource: HotelNetworkDataSource): HotelRepository {

    init {

    }

    override suspend fun getHotels(): LiveData<HotelDTO> {

    }

}

