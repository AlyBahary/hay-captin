package com.beetleware.hayatiDeliveryMan.ui.fragments.orders

import com.beetleware.hayatiDeliveryMan.ui.base.BaseView

interface OrdersView : BaseView {
    fun initOrders()
    fun openOrderDetails(orderId: String)
}