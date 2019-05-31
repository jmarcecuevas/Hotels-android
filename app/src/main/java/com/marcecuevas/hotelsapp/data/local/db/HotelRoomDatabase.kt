package com.marcecuevas.hotelsapp.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.marcecuevas.hotelsapp.data.local.dao.HotelDAO
import com.marcecuevas.hotelsapp.data.model.entity.HotelEntity

@Database(entities = [HotelEntity::class], version = 1)
abstract class HotelRoomDatabase: RoomDatabase() {

    abstract fun hotelDAO(): HotelDAO

        companion object {
            @Volatile private var instance: HotelRoomDatabase? = null
            private val LOCK = Any()

            operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
                instance ?: buildDatabase(context).also { instance = it }
            }

            private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                    HotelRoomDatabase::class.java, "hotels.db")
                    .build()
        }

    }