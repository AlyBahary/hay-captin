package com.beetleware.hayatiDeliveryMan.ui.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.beetleware.hayatiDeliveryMan.R
import com.beetleware.hayatiDeliveryMan.common.Constants
import com.beetleware.hayatiDeliveryMan.data.network.model.OrderResponseModel
import com.beetleware.hayatiDeliveryMan.databinding.ItemOrdersBinding
import com.beetleware.hayatiDeliveryMan.ui.base.BasePagedListAdapter
import com.beetleware.hayatiDeliveryMan.ui.fragments.orders.OrdersView

class OrderPagedListAdapter(
    private val ordersView: OrdersView,
    private val type: String,
    val context: Context
) : BasePagedListAdapter() {

    override fun createBinding(parent: ViewGroup, viewType: Int) = DataBindingUtil.inflate(
        LayoutInflater.from(parent.context), R.layout.item_orders, parent, false
    ) as ItemOrdersBinding

    override fun bind(binding: ViewDataBinding, position: Int) {
        if (type == Constants.ORDER) {
            val order = getItem(position) as OrderResponseModel.Orders.Data
            (binding as ItemOrdersBinding).tvPrice.text = order.total_amount.toString()
            binding.tvStatus.text = order.payment_status
            binding.tvDate.text = order.order_date
            binding.tvPrice.text =
                order.total_amount.toString() + " " + context.getString(R.string.rs)
            binding.tvLocation.text = order.location
            binding.tvOrderNumber.text = context.getString(R.string.order_hash) + order.order_number
            binding.containerForOrder.setOnClickListener {
                ordersView.openOrderDetails(order.order_id.toString())
            }
        } else {
            val order = getItem(position) as OrderResponseModel.Subscription.Data
            (binding as ItemOrdersBinding).tvPrice.text = order.total_amount.toString()
            binding.tvStatus.text = order.payment_status
            binding.tvDate.text = order.order_date
            binding.tvPrice.text =
                order.total_amount.toString() + " " + context.getString(R.string.rs)
            binding.tvLocation.text = "order.location"
            binding.tvOrderNumber.text = context.getString(R.string.order_hash) + order.subscription_id
            binding.containerForOrder.setOnClickListener {
                ordersView.openOrderDetails(order.subscription_id.toString())
            }
        }


    }
}