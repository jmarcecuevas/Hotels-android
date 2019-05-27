package com.marcecuevas.hotelsapp.data.dao

import androidx.lifecycle.LiveData
import com.marcecuevas.hotelsapp.data.dao.common.GenericApiDAO
import com.marcecuevas.hotelsapp.data.model.DTO.HotelDTO
import com.marcecuevas.hotelsapp.data.model.DTO.HotelDetailDTO

class HotelApiDAO: GenericApiDAO(),HotelDAO {

    override fun findAll(): LiveData<List<HotelDTO>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun findHotelDetail(id: String): LiveData<HotelDetailDTO> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}