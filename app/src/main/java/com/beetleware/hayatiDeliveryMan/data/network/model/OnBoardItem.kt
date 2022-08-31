package com.beetleware.hayatiDeliveryMan.data.network.model

import com.google.gson.annotations.SerializedName

data class OnBoardItem (
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("image")
    val image: Int
)