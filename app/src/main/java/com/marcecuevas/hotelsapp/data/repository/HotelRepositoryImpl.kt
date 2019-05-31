package com.marcecuevas.hotelsapp.data.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.marcecuevas.hotelsapp.data.local.dao.HotelDAO
import com.marcecuevas.hotelsapp.data.model.DTO.HotelDTO
import com.marcecuevas.hotelsapp.data.model.DTO.HotelDetailDTO
import com.marcecuevas.hotelsapp.data.model.Result
import com.marcecuevas.hotelsapp.data.model.entity.HotelEntity
import com.marcecuevas.hotelsapp.data.network.HotelNetworkDataSource


class HotelRepositoryImpl(private val hotelNetworkDataSource: HotelNetworkDataSource,
                          private val hotelDAO: HotelDAO): HotelRepository {

    override val allViewdHotels: LiveData<List<HotelEntity>>
        get() = hotelDAO.getViewedHotels()

    @WorkerThread
    override suspend fun insertViewed(hotel: HotelEntity) {
        hotelDAO.insert(hotel)
    }

    override suspend fun getHotels(): Result<HotelDTO> {
        return hotelNetworkDataSource.getHotels()
    }

    override suspend fun getHotelDetail(id: String): Result<HotelDetailDTO> {
        return hotelNetworkDataSource.getHotelDetail(id)
    }
}

