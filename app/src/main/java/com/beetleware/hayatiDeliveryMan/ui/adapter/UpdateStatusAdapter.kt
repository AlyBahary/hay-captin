package com.beetleware.hayatiDeliveryMan.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.beetleware.hayatiDeliveryMan.R
import com.beetleware.hayatiDeliveryMan.data.network.model.OrderStatus
import com.beetleware.hayatiDeliveryMan.databinding.ItemUpdateStatusBinding
import com.beetleware.hayatiDeliveryMan.ui.base.BaseViewHolder
import com.beetleware.hayatiDeliveryMan.ui.bottom_sheet.change_status.ChangeStatusView
import com.bumptech.glide.Glide

class UpdateStatusAdapter(
    private val status: ArrayList<OrderStatus>, val changeStatusView: ChangeStatusView, val context: Context
) : RecyclerView.Adapter<UpdateStatusAdapter.UpdateStatusViewHolder>() {

    var paymentId =""
    private var lastCheckedPosition: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpdateStatusViewHolder {
        val binding: ItemUpdateStatusBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_update_status,
            parent, false
        )
        return UpdateStatusViewHolder(binding)
    }

    override fun getItemCount() = status.size

    override fun onBindViewHolder(holder: UpdateStatusViewHolder, position: Int) {
        holder.binding.statusName.text = status[position].name
        Glide.with(context).load(status[position].image).into(holder.binding.imgPayment)
        holder.binding.radioStatus.setOnClickListener {
            holder.binding.root.performClick()
        }
        holder.binding.root.setOnClickListener {
            val copyOfLastCheckedPosition: Int = lastCheckedPosition
            lastCheckedPosition = position
            notifyItemChanged(copyOfLastCheckedPosition)
            notifyItemChanged(lastCheckedPosition)
            changeStatusView.changeStatus(position.toString())

        }
        holder.binding.radioStatus.isChecked = position == lastCheckedPosition;

    }

    inner class UpdateStatusViewHolder(binding: ItemUpdateStatusBinding) :
        BaseViewHolder<ItemUpdateStatusBinding>(binding)

}