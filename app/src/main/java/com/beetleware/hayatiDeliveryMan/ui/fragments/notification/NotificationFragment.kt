package com.beetleware.hayatiDeliveryMan.ui.fragments.notification

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.beetleware.hayatiDeliveryMan.R
import com.beetleware.hayatiDeliveryMan.databinding.FragmentNotificationBinding
import com.beetleware.hayatiDeliveryMan.ui.adapter.NotificationAdapter
import com.beetleware.hayatiDeliveryMan.ui.base.BaseFragment

class NotificationFragment : NotificationView, BaseFragment<NotificationViewModel, FragmentNotificationBinding>(
    NotificationViewModel::class.java) {

    override fun getLayoutRes() = R.layout.fragment_notification

    override fun initViewModel(viewModel: NotificationViewModel) {
        mBinding.viewModel=viewModel
    }

    override fun init(savedInstanceState: Bundle?) {
        initNotifications()
        mBinding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun initNotifications(){
        viewModel.getNotifications().observe(this, Observer {
            if(it.isResponseSuccessful){
                if(it.responseBody!!.data.data.isEmpty()){
                    mBinding.containerEmpty.visibility= View.VISIBLE
                    mBinding.rcNotifications.visibility=View.GONE
                }else{
                    mBinding.containerEmpty.visibility= View.GONE
                    mBinding.rcNotifications.visibility=View.VISIBLE
                    mBinding.rcNotifications.run {
                        layoutManager = LinearLayoutManager(context)
                        adapter = NotificationAdapter(it.responseBody!!.data.data, this@NotificationFragment, context)
                    }
                }
            }
        })
    }

}