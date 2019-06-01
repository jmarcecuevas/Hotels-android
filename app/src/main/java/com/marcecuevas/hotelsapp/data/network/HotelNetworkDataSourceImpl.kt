package com.marcecuevas.hotelsapp.data.network

import com.marcecuevas.hotelsapp.data.model.DTO.HotelDTO
import com.marcecuevas.hotelsapp.data.model.DTO.HotelDetailDTO
import com.marcecuevas.hotelsapp.data.model.Result
import com.marcecuevas.hotelsapp.utils.NoConnectivityException
import java.io.IOException

class HotelNetworkDataSourceImpl(private val hotelREST: HotelREST): HotelNetworkDataSource{

    override suspend fun getHotelDetail(id: String): Result<HotelDetailDTO> {
        try{
            val response = hotelREST.getHotelDetail(id).await()
            if(response.isSuccessful){
                response.body()?.let {
                    return Result.Success(it)
                }
            }
            return Result.Error(IOException("Ha ocurrido un error"))
        }catch (e: NoConnectivityException){
            return Result.Error(IOException("No hay internet"))
        }
    }

    override suspend fun getHotels(): Result<HotelDTO> {
        try{
            val response = hotelREST.getHotels().await()
            if (response.isSuccessful)
                response.body()?.let {
                    return Result.Success(it)
                }
            return Result.Error(IOException("Ha ocurrido un error"))
        }catch (e: NoConnectivityException){
            return Result.Error(IOException("No hay internet"))
        }

    }
}