package com.beetleware.hayatiDeliveryMan.data.network.model

import com.beetleware.hayatiDeliveryMan.ui.base.BaseEntity
import com.google.gson.annotations.SerializedName

data class GeneralResponse<T> (
    @SerializedName("data")
    val data: T?,
    @SerializedName("error")
    val error: ErrorResponse,
    @SerializedName("msg")
    val successMessage:String
):BaseEntity()