package com.beetleware.hayatiDeliveryMan.data.network.model


import com.google.gson.annotations.SerializedName

data class GetNotificationsResponse(
    @SerializedName("data")
    val data: Data,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("status")
    val status: Int
) {
    data class Data(
        @SerializedName("current_page")
        val currentPage: Int,
        @SerializedName("data")
        val data: ArrayList<Notifications>,
        @SerializedName("first_page_url")
        val firstPageUrl: String,
        @SerializedName("from")
        val from: Int,
        @SerializedName("last_page")
        val lastPage: Int,
        @SerializedName("last_page_url")
        val lastPageUrl: String,
        @SerializedName("next_page_url")
        val nextPageUrl: String,
        @SerializedName("path")
        val path: String,
        @SerializedName("per_page")
        val perPage: Int,
        @SerializedName("prev_page_url")
        val prevPageUrl: Any,
        @SerializedName("to")
        val to: Int,
        @SerializedName("total")
        val total: Int
    ) {
        data class Notifications(
            @SerializedName("content")
            val content: String,
            @SerializedName("created_at")
            val createdAt: String,
            @SerializedName("heading")
            val heading: String,
            @SerializedName("id")
            val id: Int,
            @SerializedName("info")
            val info: Int,
            @SerializedName("is_read")
            val isRead: Boolean,
            @SerializedName("notification_key")
            val notificationKey: Int,
            @SerializedName("notification_type")
            val notificationType: String,
            @SerializedName("updated_at")
            val updatedAt: String
        )
    }
}