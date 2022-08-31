package com.beetleware.hayatiDeliveryMan.ui.fragments.home

import com.beetleware.hayatiDeliveryMan.data.network.model.HomeRespone
import com.beetleware.hayatiDeliveryMan.ui.base.BaseView

interface HomeView : BaseView {
    fun initStatics(new: String, done: String, cancel: String)
    fun initIncomingOrders(data: ArrayList<HomeRespone.Orders.Data>)
    fun initIncomingSubOrders(data: ArrayList<HomeRespone.SubscriptionResponseModel.Data>)
    fun openOrderDetails(orderId: String,type: String)
}