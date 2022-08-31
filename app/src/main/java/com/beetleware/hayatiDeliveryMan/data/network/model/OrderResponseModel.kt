package com.beetleware.hayatiDeliveryMan.data.network.model

data class OrderResponseModel(
    val orders: Orders,
    val subscription: Subscription
) {
    data class Orders(
        val current_page: Int,
        val `data`: List<Data>,
        val first_page_url: String,
        val from: Int,
        val last_page: Int,
        val last_page_url: String,
        val next_page_url: Any,
        val path: String,
        val per_page: Int,
        val prev_page_url: Any,
        val to: Int,
        val total: Int
    ) {
        data class Data(
            val location: String,
            val order_date: String,
            val order_id: String,
            val order_number: Int,
            val payment_status: String,
            val total_amount: Double
        )
    }

    data class Subscription(
        val current_page: Int,
        val `data`: List<Data>,
        val first_page_url: String,
        val from: Int,
        val last_page: Int,
        val last_page_url: String,
        val next_page_url: Any,
        val path: String,
        val per_page: Int,
        val prev_page_url: Any,
        val to: Int,
        val total: Int
    ) {
        data class Data(
            val order_date: String,
            val payment_status: String,
            val subscription_id: Int,
            val total_amount: Double
        )
    }
}
