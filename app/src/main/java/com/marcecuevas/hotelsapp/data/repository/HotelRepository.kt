package com.marcecuevas.hotelsapp.data.repository

import androidx.lifecycle.LiveData
import com.marcecuevas.hotelsapp.data.dao.common.Crudable
import com.marcecuevas.hotelsapp.data.model.DTO.HotelDTO
import com.marcecuevas.hotelsapp.data.model.DTO.HotelDetailDTO

interface HotelRepository: Crudable<HotelDTO> {

    fun getHotelDetail(id: String): LiveData<HotelDetailDTO>
}