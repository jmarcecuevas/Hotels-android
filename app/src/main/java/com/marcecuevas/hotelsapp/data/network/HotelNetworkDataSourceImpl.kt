package com.marcecuevas.hotelsapp.data.network

import com.marcecuevas.hotelsapp.data.model.DTO.HotelDTO
import com.marcecuevas.hotelsapp.data.model.MResult
import java.io.IOException

class HotelNetworkDataSourceImpl(private val hotelREST: HotelREST): HotelNetworkDataSource {

    override suspend fun getHotels(): MResult<HotelDTO> {
        val response = hotelREST.getHotels().await()
        if(response.isSuccessful) {
            response.body()?.let {
                return MResult.Success(it)
            }
        return MResult.Error("An error ocurred.")
    }





//    private val hotelsLiveData = MutableLiveData<HotelDTO>()
//
//    override val hotels: LiveData<HotelDTO>
//        get() = hotelsLiveData


//    override suspend fun getHotels() {
//        try {
//            val fetchedHotels = hotelREST
//                .getHotels()
//                .await()
//            hotelsLiveData.postValue(fetchedHotels)
//        }
//        catch (e: NoConnectivityException){
//            Log.e("Connectivity","No internet connection.",e)
//        }
//    }
}