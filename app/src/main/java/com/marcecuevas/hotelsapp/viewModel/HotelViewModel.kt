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

    private val _hotels = MutableLiveData<HotelDTO>()
    private val _error = MutableLiveData<String>()

    private val _hotelDetail = MutableLiveData<HotelDetailDTO>()

    val hotels: LiveData<HotelDTO> get() = _hotels
    val hotelDetail: LiveData<HotelDetailDTO> get() = _hotelDetail
    val error: MutableLiveData<String> get() = _error

    private var job: Job? = null

    init {
        this.initGetHotelsCall()
    }

    val allViewed: LiveData<List<HotelEntity>> = repository.allViewdHotels

    private fun initGetHotelsCall(){
        job = GlobalScope.launch {
            val value = repository.getHotels()
            when(value){
                is Result.Success -> _hotels.postValue(value.data)
                is Result.Error -> _error.postValue(value.message.message)
            }
        }
    }

    fun getHotelDetail(id: String){
        job = GlobalScope.launch {
            val value = repository.getHotelDetail(id)
            when(value){
                is Result.Success -> _hotelDetail.postValue(value.data)
                is Result.Error -> _error.postValue(value.message.message)
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