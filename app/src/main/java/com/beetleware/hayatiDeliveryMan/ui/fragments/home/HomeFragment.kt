package com.beetleware.hayatiDeliveryMan.ui.fragments.home

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.beetleware.hayatiDeliveryMan.R
import com.beetleware.hayatiDeliveryMan.data.network.model.HomeRespone
import com.beetleware.hayatiDeliveryMan.data.network.model.StaticsModel
import com.beetleware.hayatiDeliveryMan.databinding.FragmentHomeBinding
import com.beetleware.hayatiDeliveryMan.ui.adapter.IncomingOrdersAdapter
import com.beetleware.hayatiDeliveryMan.ui.adapter.StaticsAdapter
import com.beetleware.hayatiDeliveryMan.ui.adapter.SubscriptionOrdersAdapter
import com.beetleware.hayatiDeliveryMan.ui.base.BaseFragment

class HomeFragment : HomeView , BaseFragment<HomeViewModel,FragmentHomeBinding>(HomeViewModel::class.java) {

    override fun getLayoutRes() = R.layout.fragment_home

    override fun initViewModel(viewModel: HomeViewModel) {
        mBinding.viewModel=viewModel
    }

    override fun init(savedInstanceState: Bundle?) {
        viewModel.home().observe(this, Observer {
            if(it.isResponseSuccessful){
                initStatics(it.responseBody!!.data!!.newOrders,it.responseBody!!.data!!.deliveriedOrders,it.responseBody!!.data!!.canceledOrders)
                initIncomingOrders(it.responseBody!!.data!!.orders.data)
                initIncomingSubOrders(it.responseBody!!.data!!.subscriptionResponseModel.data)
            }
        })

        mBinding.btnNotification.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_notificationFragment)
        }
    }

    override fun initStatics(new:String,done:String,cancel:String){
        val statics = ArrayList<StaticsModel>()
        val staticsOne = StaticsModel(getString(R.string.new_order),new,R.drawable.radius_statics_one)
        val staticsTwo = StaticsModel(getString(R.string.delivered),done,R.drawable.radius_statics)
        val staticsThree = StaticsModel(getString(R.string.cancelled),cancel,R.drawable.radius_statics_three)

        statics.add(staticsOne)
        statics.add(staticsTwo)
        statics.add(staticsThree)
        mBinding.rcStatics.run {
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
            adapter = StaticsAdapter(statics, this@HomeFragment, context)
        }
    }

    override fun initIncomingOrders(data:ArrayList<HomeRespone.Orders.Data>){
        mBinding.rcIncomingRequests.run {
            layoutManager = LinearLayoutManager(context)
            adapter = IncomingOrdersAdapter(data, this@HomeFragment, context)
        }
    }
 override fun initIncomingSubOrders(data:ArrayList<HomeRespone.SubscriptionResponseModel.Data>){
        mBinding.rcIncomingSubRequests .run {
            layoutManager = LinearLayoutManager(context)
            adapter = SubscriptionOrdersAdapter(data, this@HomeFragment, context)
        }
    }

    override fun openOrderDetails(orderId: String,type:String) {
        val action = HomeFragmentDirections.actionHomeFragmentToOrderDetailsFragment(orderId,type)
        findNavController().navigate(action)
    }
}