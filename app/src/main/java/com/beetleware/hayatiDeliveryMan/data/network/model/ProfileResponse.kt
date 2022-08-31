package com.beetleware.hayatiDeliveryMan.data.network.model

import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @SerializedName("address")
    val address: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("notification_receive")
    val notificationReceive:Boolean,
    @SerializedName("notification_sound")
    val notificationSound:Boolean
)