package com.marcecuevas.hotelsapp.data.network

import com.marcecuevas.hotelsapp.data.model.DTO.HotelDTO
import com.marcecuevas.hotelsapp.data.model.DTO.HotelDetailDTO
import com.marcecuevas.hotelsapp.data.model.Result
import java.io.IOException

class HotelNetworkDataSourceImpl(private val hotelREST: HotelREST): HotelNetworkDataSource{

    override suspend fun getHotelDetail(id: String): Result<HotelDetailDTO> {
        val response = hotelREST.getHotelDetail(id).await()
        if(response.isSuccessful){
            response.body()?.let {
                return Result.Success(it)
            }
        }
        return Result.Error(IOException("Error ocurred"))
    }

    override suspend fun getHotels(): Result<HotelDTO> {
        val response = hotelREST.getHotels().await()
        if (response.isSuccessful)
            response.body()?.let {
                return Result.Success(it)
            }
        return Result.Error(IOException("Error ocurred"))
    }
}