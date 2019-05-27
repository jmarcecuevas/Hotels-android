package com.marcecuevas.hotelsapp.data.model.DTO

import com.google.gson.annotations.SerializedName

open class GenericDTO<T>(
    @SerializedName("meta_data") val metadata: MetadataDTO,
    val items: List<T>
)


class MetadataDTO(
    val message: String,
    val code: String,
    val uow: String
)
