package com.beetleware.hayatiDeliveryMan.data.network.model

import com.google.gson.annotations.SerializedName

data class TermsAndConditionsResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("value")
    val value: String
)