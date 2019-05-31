package com.marcecuevas.hotelsapp.data.model.DTO

import com.google.gson.annotations.SerializedName

data class HotelDetailDTO(
    val id: String?,
    val hotel: HotelDetailItemDTO?,
    val price: PriceDTO?
): GenericDTO

data class HotelDetailItemDTO(
    val id: String?,
    val description: String?,
    val stars: Int?,
    val name: String?,
    val address: String?,
    @SerializedName("main_picture") val mainPicture: String?,
    val rating: Double?,
    @SerializedName("geo_location") val geoLocation: GeolocationDTO?,
    val city: CityDTO?,
    val reviews: List<ReviewDTO>?,
    val amenities: List<AmenityDTO>?
)

data class GeolocationDTO(
    val latitude: String?,
    val longitude: String?
)

data class CityDTO(
    val id: String?,
    val code: String?,
    val name: String?,
    val country: CountryDTO?,
    @SerializedName("administrative_division") val administrativeDivision:
            AdministrativeDivisionDTO?
)

data class CountryDTO(
    val id: String?,
    val code: String?,
    val name: String?
)

data class AdministrativeDivisionDTO(
    val id: String?,
    val code: String?,
    val name: String?
)

data class ReviewDTO(
    val comments: CommentDTO?,
    val user: UserDTO?
)

data class CommentDTO(
    val good: String?,
    val bad: String?,
    val type: String?
)

data class UserDTO(
    val name: String?,
    @SerializedName("first_name") val firstName: String?,
    val country: String?
)
