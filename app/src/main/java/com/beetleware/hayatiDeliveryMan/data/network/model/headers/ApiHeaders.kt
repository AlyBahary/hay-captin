package com.beetleware.hayatiDeliveryMan.data.network.model.headers

data class ApiHeaders(
    var x_lang: String? = null,
    var authorization: String? = null,
    var content_type: String = "application/json"
)
