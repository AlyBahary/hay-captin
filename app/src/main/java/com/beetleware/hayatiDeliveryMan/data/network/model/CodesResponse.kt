package com.beetleware.hayatiDeliveryMan.data.network.model


import com.google.gson.annotations.SerializedName

data class CodesResponse(
    @SerializedName("current_page")
    val currentPage: Int,
    @SerializedName("data")
    val data: ArrayList<Data>
) {
    data class Data(
        @SerializedName("code")
        val code: Int,
        @SerializedName("created_at")
        val createdAt: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("updated_at")
        val updatedAt: String
    )
}