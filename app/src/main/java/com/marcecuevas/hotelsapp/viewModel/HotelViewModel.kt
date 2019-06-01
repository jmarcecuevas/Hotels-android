package com.marcecuevas.hotelsapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marcecuevas.hotelsapp.data.model.DTO.HotelDTO
import com.marcecuevas.hotelsapp.data.model.DTO.HotelDetailDTO
import com.marcecuevas.hotelsapp.data.repository.HotelRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import com.marcecuevas.hotelsapp.data.model.Result
import com.marcecuevas.hotelsapp.data.model.entity.HotelEntity
import kotlinx.coroutines.Dispatchers


class HotelViewModel(val repository: HotelRepository): ViewModel() {

    private val hotels = MutableLiveData<HotelDTO>()
    private val error = MutableLiveData<String>()

    private val hotelDetail = MutableLiveData<HotelDetailDTO>()

    val hotelsLivedata: LiveData<HotelDTO> get() = hotels
    val hotelDetailLiveData: LiveData<HotelDetailDTO> get() = hotelDetail
    val errorLiveData: MutableLiveData<String> get() = error

    private var job: Job? = null

    init {
        this.initGetHotelsCall()
    }

    val allViewed: LiveData<List<HotelEntity>> = repository.allViewdHotels

    private fun initGetHotelsCall(){
        job = GlobalScope.launch {
            val value = repository.getHotels()
            when(value){
                is Result.Success -> hotels.postValue(value.data)
                is Result.Error -> error.postValue(value.message.message)
            }
        }
    }

    fun getHotelDetail(id: String){
        job = GlobalScope.launch {
            val value = repository.getHotelDetail(id)
            when(value){
                is Result.Success -> hotelDetail.postValue(value.data)
                is Result.Error -> error.postValue(value.message.message)
            }
        }
    }

    fun insertViewedHotel(hotel: HotelEntity) {
        job = GlobalScope.launch(Dispatchers.IO) {
            repository.insertViewed(hotel)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }




}