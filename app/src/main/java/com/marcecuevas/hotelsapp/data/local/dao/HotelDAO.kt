package com.marcecuevas.hotelsapp.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.marcecuevas.hotelsapp.data.model.entity.HotelEntity
import com.marcecuevas.hotelsapp.data.network.ConnectivityInterceptor
import com.marcecuevas.hotelsapp.data.network.HotelREST

@Dao
interface HotelDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(hotel: HotelEntity)

    @Query("SELECT * from viewed_hotel")
    fun getViewedHotels(): LiveData<List<HotelEntity>>
}