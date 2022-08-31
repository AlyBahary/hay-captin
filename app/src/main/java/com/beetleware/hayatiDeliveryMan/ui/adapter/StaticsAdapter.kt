package com.beetleware.hayatiDeliveryMan.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.beetleware.hayatiDeliveryMan.R
import com.beetleware.hayatiDeliveryMan.data.network.model.StaticsModel
import com.beetleware.hayatiDeliveryMan.databinding.ItemStaticsBinding
import com.beetleware.hayatiDeliveryMan.ui.base.BaseViewHolder
import com.beetleware.hayatiDeliveryMan.ui.fragments.home.HomeView

class StaticsAdapter  (
    private val statics: ArrayList<StaticsModel>, val homeView: HomeView, val context: Context
) : RecyclerView.Adapter<StaticsAdapter.StaticsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StaticsViewHolder {
        val binding: ItemStaticsBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_statics,
            parent, false
        )
        return StaticsViewHolder(binding)
    }

    override fun getItemCount() = statics.size

    override fun onBindViewHolder(holder: StaticsViewHolder, position: Int) {
        holder.binding.tvTitle.text = statics[position].title
        holder.binding.tvNumber.text = statics[position].count
        holder.binding.containerForStatics.setBackgroundResource(statics[position].color)
    }

    inner class StaticsViewHolder(binding: ItemStaticsBinding) :
        BaseViewHolder<ItemStaticsBinding>(binding)


}