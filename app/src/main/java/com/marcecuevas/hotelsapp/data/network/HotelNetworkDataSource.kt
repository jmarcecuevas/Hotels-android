package com.marcecuevas.hotelsapp.data.network

import com.marcecuevas.hotelsapp.data.model.DTO.HotelDTO
import com.marcecuevas.hotelsapp.data.model.MResult

interface HotelNetworkDataSource {

    suspend fun getHotels(): MResult<HotelDTO>
}