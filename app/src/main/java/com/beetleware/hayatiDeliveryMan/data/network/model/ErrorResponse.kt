package com.beetleware.hayatiDeliveryMan.data.network.model

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("message")
    val message: String
)