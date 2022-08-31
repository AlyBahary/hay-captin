package com.beetleware.hayatiDeliveryMan.data.network.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("access_token")
    val accessToken: String
)