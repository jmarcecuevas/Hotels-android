package com.marcecuevas.hotelsapp.data.repository

import androidx.lifecycle.LiveData
import com.marcecuevas.hotelsapp.data.model.DTO.HotelDTO
import com.marcecuevas.hotelsapp.data.model.DTO.HotelDetailDTO
import com.marcecuevas.hotelsapp.data.model.Result
import com.marcecuevas.hotelsapp.data.model.entity.HotelEntity

interface HotelRepository {

    val allViewdHotels: LiveData<List<HotelEntity>>

    suspend fun getHotels(): Result<HotelDTO>

    suspend fun getHotelDetail(id: String): Result<HotelDetailDTO>

    suspend fun insertViewed(hotel: HotelEntity)


}