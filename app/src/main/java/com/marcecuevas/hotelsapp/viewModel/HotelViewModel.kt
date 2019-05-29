package com.marcecuevas.hotelsapp.viewModel

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


class HotelViewModel(val repository: HotelRepository): ViewModel(){

    private val _hotels = MutableLiveData<HotelDetailDTO>()
    private val _error = MutableLiveData<String>()

    val hotels: LiveData<HotelDetailDTO> get() = _hotels
    val error: MutableLiveData<String> get() = _error

    private var job: Job? = null

    init {
        this.initGetHotelsCall()
    }

    private fun initGetHotelsCall(){
        job = GlobalScope.launch {
            val value = repository.getHotelDetail("364650")
            when(value){
                is Result.Success -> _hotels.postValue(value.data)
                is Result.Error -> _error.postValue(value.message.message)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }


}