package com.marcecuevas.hotelsapp.data.dao

import androidx.lifecycle.LiveData
import com.marcecuevas.hotelsapp.data.model.DTO.HotelDTO
import com.marcecuevas.hotelsapp.data.model.DTO.HotelDetailDTO

class HotelLocalDAO: HotelDAO {

    override fun findHotelDetail(id: String): LiveData<HotelDetailDTO> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun findAll(): LiveData<List<HotelDTO>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}