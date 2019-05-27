package com.marcecuevas.hotelsapp.data.dao.common

import androidx.lifecycle.LiveData
import com.marcecuevas.hotelsapp.data.model.DTO.GenericDTO

interface Crudable<T:GenericDTO> {

    fun findAll(): LiveData<List<T>>
}