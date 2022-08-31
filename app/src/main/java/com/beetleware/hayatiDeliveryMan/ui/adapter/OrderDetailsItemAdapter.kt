package com.beetleware.hayatiDeliveryMan.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.beetleware.hayatiDeliveryMan.R
import com.beetleware.hayatiDeliveryMan.common.Constants
import com.beetleware.hayatiDeliveryMan.common.extensions.loadImg
import com.beetleware.hayatiDeliveryMan.data.network.model.OrderDetailsResponse
import com.beetleware.hayatiDeliveryMan.databinding.ItemOrderDetailsBinding
import com.beetleware.hayatiDeliveryMan.ui.base.BaseViewHolder
import com.beetleware.hayatiDeliveryMan.ui.fragments.order_details.OrderDetailsView
import java.lang.Exception

class OrderDetailsItemAdapter(
    private val order: ArrayList<OrderDetailsResponse.OrderItem>,private val type:String, val orderDetailsView: OrderDetailsView, val context: Context
) : RecyclerView.Adapter<OrderDetailsItemAdapter.OrderDetailsItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderDetailsItemViewHolder {
        val binding: ItemOrderDetailsBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_order_details,
            parent, false
        )
        return OrderDetailsItemViewHolder(binding)
    }

    override fun getItemCount() = order.size

    override fun onBindViewHolder(holder: OrderDetailsItemViewHolder, position: Int) {
        holder.binding.tvPrice.text = (order[position].price*order[position].quantity).toString()
        holder.binding.tvHeader.text = order[position].name
        holder.binding.tvQuantity.text =context.getString(R.string.quantity)+" "+ order[position].quantity.toString()
        holder.binding.imgLogo.loadImg(order[position].logo,R.drawable.hyati_logo)
        try {
            var description = ""
            for(i in order[position].options){
                description = "$description $i"
            }
            holder.binding.tvDescription.text = description
        } catch (e: Exception) {

        }
        if (type==Constants.SUBSCRIPTION.toLowerCase()){
            holder.binding.tvPrice.text =""
            holder.binding.tvQuantity.text =""

        }
    }

    inner class OrderDetailsItemViewHolder(binding: ItemOrderDetailsBinding) :
        BaseViewHolder<ItemOrderDetailsBinding>(binding)

}