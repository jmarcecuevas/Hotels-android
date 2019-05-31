package com.marcecuevas.hotelsapp.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "viewed_hotel")
class HotelEntity(@PrimaryKey @ColumnInfo(name = "id") val id: String,
                  @ColumnInfo(name = "name") val name: String?,
                  @ColumnInfo(name = "address") val address: String?,
                  @ColumnInfo(name = "stars") val stars: Int?,
                  @ColumnInfo(name="currency_mask") val currencyMask:String?,
                  @ColumnInfo(name="price") val price: Int?,
                  @ColumnInfo(name="imageURL") val imageURL: String?)