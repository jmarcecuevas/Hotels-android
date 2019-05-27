package com.marcecuevas.hotelsapp.data.dao

import androidx.lifecycle.LiveData
import com.marcecuevas.hotelsapp.data.dao.common.Crudable
import com.marcecuevas.hotelsapp.data.model.DTO.HotelDTO
import com.marcecuevas.hotelsapp.data.model.DTO.HotelDetailDTO

interface HotelDAO: Crudable<HotelDTO> {

    fun findHotelDetail(id: String): LiveData<HotelDetailDTO>
}