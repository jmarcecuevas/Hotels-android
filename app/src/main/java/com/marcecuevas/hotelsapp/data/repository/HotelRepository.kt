package com.marcecuevas.hotelsapp.data.repository

import androidx.lifecycle.LiveData
import com.marcecuevas.hotelsapp.data.model.DTO.HotelDTO

interface HotelRepository {

    suspend fun getHotels(): LiveData<HotelDTO>
}