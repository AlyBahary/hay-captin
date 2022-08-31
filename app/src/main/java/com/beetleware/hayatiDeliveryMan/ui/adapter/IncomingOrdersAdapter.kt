package com.beetleware.hayatiDeliveryMan.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.beetleware.hayatiDeliveryMan.R
import com.beetleware.hayatiDeliveryMan.data.network.model.HomeRespone
import com.beetleware.hayatiDeliveryMan.databinding.ItemIncomingOrdersBinding
import com.beetleware.hayatiDeliveryMan.ui.base.BaseViewHolder
import com.beetleware.hayatiDeliveryMan.ui.fragments.home.HomeView

class IncomingOrdersAdapter (
    private val incomingOrders: ArrayList<HomeRespone.Orders.Data>, val homeView: HomeView, val context: Context
) : RecyclerView.Adapter<IncomingOrdersAdapter.IncomingOrdersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncomingOrdersViewHolder {
        val binding: ItemIncomingOrdersBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_incoming_orders,
            parent, false
        )
        return IncomingOrdersViewHolder(binding)
    }

    override fun getItemCount() = incomingOrders.size

    override fun onBindViewHolder(holder: IncomingOrdersViewHolder, position: Int) {
        holder.binding.containerForOrder.setOnClickListener {
            homeView.openOrderDetails(incomingOrders[position].orderId,"order")
        }
        holder.binding.tvStatus.text = incomingOrders[position].paymentStatus
        holder.binding.tvDate.text = incomingOrders[position].orderDate
        holder.binding.tvLocation.text = incomingOrders[position].location
        holder.binding.tvPrice.text = incomingOrders[position].totalAmount +context.getString(R.string.rs)
        holder.binding.tvOrderNumber.text = context.getString(R.string.order_hash)+incomingOrders[position].orderNumber
    }

    inner class IncomingOrdersViewHolder(binding: ItemIncomingOrdersBinding) :
        BaseViewHolder<ItemIncomingOrdersBinding>(binding)

}