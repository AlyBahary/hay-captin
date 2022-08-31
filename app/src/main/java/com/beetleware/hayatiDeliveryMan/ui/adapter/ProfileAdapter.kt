package com.beetleware.hayatiDeliveryMan.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.beetleware.hayatiDeliveryMan.R
import com.beetleware.hayatiDeliveryMan.databinding.ItemProfileBinding
import com.beetleware.hayatiDeliveryMan.ui.base.BaseViewHolder
import com.beetleware.hayatiDeliveryMan.ui.fragments.profile.ProfileView


class ProfileAdapter(
    private val profile: ArrayList<String>, val profileView: ProfileView, val context: Context
) : RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val binding: ItemProfileBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_profile,
            parent, false
        )
        return ProfileViewHolder(binding)
    }

    override fun getItemCount() = profile.size

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.binding.tvProfileName.text = profile[position]
        holder.binding.containerForProfile.setOnClickListener {
            profileView.openNested(position)
        }
    }

    inner class ProfileViewHolder(binding: ItemProfileBinding) :
        BaseViewHolder<ItemProfileBinding>(binding)

}