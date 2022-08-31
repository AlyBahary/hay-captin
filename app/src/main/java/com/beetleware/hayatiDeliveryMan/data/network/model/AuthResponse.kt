package com.beetleware.hayatiDeliveryMan.data.network.model


import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("token")
    val token: String
)