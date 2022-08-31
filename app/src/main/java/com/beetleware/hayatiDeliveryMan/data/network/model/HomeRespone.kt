package com.beetleware.hayatiDeliveryMan.data.network.model


import com.google.gson.annotations.SerializedName

data class HomeRespone(
    @SerializedName("canceled_orders")
    val canceledOrders: String,
    @SerializedName("deliveried_orders")
    val deliveriedOrders: String,
    @SerializedName("new_orders")
    val newOrders: String,
    @SerializedName("orders")
    val orders: Orders,
    @SerializedName("subscription")
    val subscriptionResponseModel: SubscriptionResponseModel
) {
    data class SubscriptionResponseModel(
        @SerializedName("data")
        val data: ArrayList<SubscriptionResponseModel.Data>

    ) {
        data class Data(
            @SerializedName("location")
            val location: String,

            @SerializedName("order_date")
            val orderDate: String,

            @SerializedName("payment_status")
            val paymentStatus: String,

            @SerializedName("subscription_id")
            val subscription_id: String,
            @SerializedName("total_amount")
            val totalAmount: String
        )
    }


    data class Orders(
        @SerializedName("current_page")
        val currentPage: Int,
        @SerializedName("data")
        val data: ArrayList<Data>,
        @SerializedName("first_page_url")
        val firstPageUrl: String,
        @SerializedName("from")
        val from: Int,
        @SerializedName("last_page")
        val lastPage: Int,
        @SerializedName("last_page_url")
        val lastPageUrl: String,
        @SerializedName("next_page_url")
        val nextPageUrl: Any,
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
        data class Data(
            @SerializedName("location")
            val location: String,
            @SerializedName("order_date")
            val orderDate: String,
            @SerializedName("order_id")
            val orderId: String,
            @SerializedName("order_number")
            val orderNumber: String,
            @SerializedName("payment_status")
            val paymentStatus: String,
            @SerializedName("total_amount")
            val totalAmount: String
        )
    }
}