package com.marcecuevas.hotelsapp.data.network

import com.marcecuevas.hotelsapp.data.model.DTO.HotelDTO
import com.marcecuevas.hotelsapp.data.model.DTO.HotelDetailDTO
import com.marcecuevas.hotelsapp.data.model.Result

interface HotelNetworkDataSource {

    suspend fun getHotels(): Result<HotelDTO>

    suspend fun getHotelDetail(id: String): Result<HotelDetailDTO>
}