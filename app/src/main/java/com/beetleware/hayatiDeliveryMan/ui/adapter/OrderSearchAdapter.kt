package com.beetleware.hayatiDeliveryMan.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.beetleware.hayatiDeliveryMan.R
import com.beetleware.hayatiDeliveryMan.common.Constants
import com.beetleware.hayatiDeliveryMan.data.network.model.OrderSearchResponseModel
import com.beetleware.hayatiDeliveryMan.databinding.ItemOrdersBinding
import com.beetleware.hayatiDeliveryMan.ui.base.BaseViewHolder
import com.beetleware.hayatiDeliveryMan.ui.fragments.orders.OrdersView

class OrderSearchAdapter(
    private val orders: OrderSearchResponseModel,
    private val type: String,
    val ordersView: OrdersView,
    val context: Context
) : RecyclerView.Adapter<OrderSearchAdapter.OrdersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersViewHolder {
        val binding: ItemOrdersBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_orders,
            parent, false
        )
        return OrdersViewHolder(binding)
    }

    override fun getItemCount() =
         orders.data.size

    override fun onBindViewHolder(holder: OrdersViewHolder, position: Int) {
        if (type == Constants.ORDER) {
            holder.binding.tvStatus.text = orders.data[position].payment_status
            holder.binding.tvDate.text = orders.data[position].order_date
            holder.binding.tvPrice.text =
                orders.data[position].total_amount.toString() + " " + context.getString(R.string.rs)
            holder.binding.tvLocation.text = orders.data[position].location
            holder.binding.tvOrderNumber.text =
                context.getString(R.string.order_hash) + orders.data[position].order_number
            holder.binding.containerForOrder.setOnClickListener {
                ordersView.openOrderDetails(orders.data[position].order_id.toString())
            }
        }else{
            holder.binding.tvStatus.text = orders.data[position].payment_status
            holder.binding.tvDate.text = orders.data[position].order_date
            holder.binding.tvPrice.text =
                orders.data[position].total_amount.toString() + " " + context.getString(R.string.rs)
            holder.binding.tvLocation.text = ""
            holder.binding.tvOrderNumber.text =
                context.getString(R.string.order_hash) + orders.data[position].subscription_id
            holder.binding.containerForOrder.setOnClickListener {
                ordersView.openOrderDetails(orders.data[position].subscription_id.toString())
            }

        }
    }

    inner class OrdersViewHolder(binding: ItemOrdersBinding) :
        BaseViewHolder<ItemOrdersBinding>(binding)


}