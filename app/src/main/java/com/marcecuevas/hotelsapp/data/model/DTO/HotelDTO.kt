package com.marcecuevas.hotelsapp.data.model.DTO

import com.google.gson.annotations.SerializedName

data class HotelDTO(
    @SerializedName("meta_data") val metadata: MetadataDTO,
    val items: List<HotelItemDTO>): GenericDTO


data class MetadataDTO(
    val message: String,
    val code: String,
    val uow: String
)

data class HotelItemDTO(
    val id: String,
    val stars: Int,
    val name: String,
    val address: String,
    @SerializedName("main_picture") val mainPicture: String,
    val rating: Double,
    val amenities: List<AmenityDTO>,
    val price: PriceDTO
)


data class AmenityDTO(
    val id: String,
    val description: String
)

data class PriceDTO(
    val currency: CurrencyDTO,
    @SerializedName("final_price") val finalPrice: Boolean,
    val base: Int,
    val best: Int
)

data class CurrencyDTO(
    val code: String,
    val mask: String,
    val ratio: Double
)

