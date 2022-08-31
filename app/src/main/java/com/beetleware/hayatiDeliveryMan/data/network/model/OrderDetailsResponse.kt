package com.beetleware.hayatiDeliveryMan.data.network.model


import com.google.gson.annotations.SerializedName

data class OrderDetailsResponse(
    @SerializedName("customer_name")
    val customerName: String,
    @SerializedName("customer_phone")
    val customerPhone: String,
    @SerializedName("location_lat")
    val locationLat: String,
    @SerializedName("location_long")
    val locationLong: String,
    @SerializedName("location_name")
    val locationName: String,
    @SerializedName("order_date")
    val orderDate: String,
    @SerializedName("order_id")
    val orderId: String,
    @SerializedName("order_items")
    val orderItems: ArrayList<OrderItem>,
    @SerializedName("order_number")
    val orderNumber: String,
    @SerializedName("order_status")
    val orderStatus: String,
    @SerializedName("payment_method")
    val paymentMethod: String,
    @SerializedName("payment_status")
    val paymentStatus: String,
    @SerializedName("total_amount")
    val totalAmount: String,
    @SerializedName("updated")
    val updated : Boolean,
    @SerializedName("next_status")
    val nextStatus :String
) {
    data class OrderItem(
        @SerializedName("banner")
        val banner: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("logo")
        val logo: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("options")
        val options: List<String>,
        @SerializedName("price")
        val price: Double,
        @SerializedName("quantity")
        val quantity: Int
    )
}