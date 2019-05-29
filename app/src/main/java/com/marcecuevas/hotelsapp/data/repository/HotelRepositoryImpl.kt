package com.marcecuevas.hotelsapp.data.repository

import com.marcecuevas.hotelsapp.data.model.DTO.HotelDTO
import com.marcecuevas.hotelsapp.data.model.DTO.HotelDetailDTO
import com.marcecuevas.hotelsapp.data.model.Result
import com.marcecuevas.hotelsapp.data.network.HotelNetworkDataSource


class HotelRepositoryImpl(private val hotelNetworkDataSource: HotelNetworkDataSource): HotelRepository {

    override suspend fun getHotels(): Result<HotelDTO> {
        return hotelNetworkDataSource.getHotels()
    }

    override suspend fun getHotelDetail(id: String): Result<HotelDetailDTO> {
        return hotelNetworkDataSource.getHotelDetail(id)
    }

}

