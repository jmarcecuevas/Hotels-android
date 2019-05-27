package com.marcecuevas.hotelsapp.data.repository

import androidx.lifecycle.LiveData
import com.marcecuevas.hotelsapp.data.dao.HotelApiDAO
import com.marcecuevas.hotelsapp.data.dao.HotelLocalDAO
import com.marcecuevas.hotelsapp.data.model.DTO.HotelDTO
import com.marcecuevas.hotelsapp.data.model.DTO.HotelDetailDTO

class HotelRepositoryImpl(val apiDAO: HotelApiDAO, val localDAO: HotelLocalDAO): HotelRepository {


    override fun findAll(): LiveData<List<HotelDTO>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getHotelDetail(id: String): LiveData<HotelDetailDTO> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}