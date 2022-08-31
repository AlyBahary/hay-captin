package com.beetleware.hayatiDeliveryMan.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.beetleware.hayatiDeliveryMan.R
import com.beetleware.hayatiDeliveryMan.data.network.model.GetNotificationsResponse
import com.beetleware.hayatiDeliveryMan.databinding.ItemNotificationBinding
import com.beetleware.hayatiDeliveryMan.ui.base.BaseViewHolder
import com.beetleware.hayatiDeliveryMan.ui.fragments.notification.NotificationView

class NotificationAdapter  (
    private val notifications: ArrayList<GetNotificationsResponse.Data.Notifications>, val notificationView: NotificationView, val context: Context
) : RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val binding: ItemNotificationBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_notification,
            parent, false
        )
        return NotificationViewHolder(binding)
    }

    override fun getItemCount() = notifications.size

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.binding.tvBody.text = notifications[position].content
        holder.binding.tvDate.text = notifications[position].updatedAt
    }

    inner class NotificationViewHolder(binding: ItemNotificationBinding) :
        BaseViewHolder<ItemNotificationBinding>(binding)

}