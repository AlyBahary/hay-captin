package com.beetleware.hayatiDeliveryMan.data.network.model


import com.google.gson.annotations.SerializedName

data class ForgetPasswordResponse(
    @SerializedName("data")
    val data: String,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("status")
    val status: Int
)