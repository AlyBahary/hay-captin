package com.beetleware.hayatiDeliveryMan.ui.fragments.order_details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.beetleware.hayatiDeliveryMan.R
import com.beetleware.hayatiDeliveryMan.common.extensions.toast
import com.beetleware.hayatiDeliveryMan.databinding.FragmentOrderDetailsBinding
import com.beetleware.hayatiDeliveryMan.ui.adapter.OrderDetailsItemAdapter
import com.beetleware.hayatiDeliveryMan.ui.base.BaseFragment
import java.util.*

class OrderDetailsFragment : OrderDetailsView ,  BaseFragment<OrderDetailsViewModel, FragmentOrderDetailsBinding>(
    OrderDetailsViewModel::class.java
) {

    val arg : OrderDetailsFragmentArgs by navArgs()
    var lat =""
    var long=""
    var nextStatus = ""


    override fun getLayoutRes() = R.layout.fragment_order_details

    override fun initViewModel(viewModel: OrderDetailsViewModel) {
        mBinding.viewModel=viewModel
    }

    override fun init(savedInstanceState: Bundle?) {
        mBinding.btnUpdateOrderStatus.setOnClickListener {
           // Here update Status
            viewModel.updateOrder(arg.order!!,arg.type.toLowerCase(),nextStatus).observe(this, Observer {
                if(it.isResponseSuccessful){
                    try {
                        nextStatus = it.responseBody!!.data!!.nextStatus
                        when(it.responseBody!!.data!!.nextStatus){
                            "ACCEPT".toLowerCase() ->{
                                mBinding.btnUpdateOrderStatus.text = getString(R.string.accept_status)
                            }
                            "ARRIVAL".toLowerCase() ->{
                                mBinding.btnUpdateOrderStatus.text = getString(R.string.arrived)
                            }
                            "DELIVERED".toLowerCase() ->{
                                mBinding.btnUpdateOrderStatus.text =  getString(R.string.delivered)
                            }
                            "HIDE".toLowerCase() ->{
                                mBinding.btnUpdateOrderStatus.visibility = View.GONE
                            }
                        }

                    }catch (e:Exception){

                    }
                    toast(getString(R.string.order_done_successfully))
                }else{
                }
            })
        }
        mBinding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
        mBinding.btnReachToMaps.setOnClickListener {
            try {
                val uri =
                    "http://maps.google.com/maps?q=loc:$lat,$long"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
                intent.setPackage("com.google.android.apps.maps")
                startActivity(intent)
            }catch (e:Exception){

            }
        }
        viewModel.getOrderDetails(arg.order,arg.type.toLowerCase()).observe(this, Observer {
            if (it.isResponseSuccessful) {
                // For handling next status
                try {
                    nextStatus = it.responseBody!!.data!!.nextStatus
                    when(it.responseBody!!.data!!.nextStatus){
                        "ACCEPT".toLowerCase() ->{
                            mBinding.btnUpdateOrderStatus.text = getString(R.string.accept_status)
                        }
                        "ARRIVAL".toLowerCase() ->{
                            mBinding.btnUpdateOrderStatus.text = getString(R.string.arrived)
                        }
                        "DELIVERED".toLowerCase() ->{
                            mBinding.btnUpdateOrderStatus.text =  getString(R.string.delivered)
                        }
                        "HIDE".toLowerCase() ->{
                            mBinding.btnUpdateOrderStatus.visibility = View.GONE
                        }
                    }
                }catch (e:Exception){

                }
                lat = it.responseBody!!.data!!.locationLat
                long = it.responseBody!!.data!!.locationLong
                viewModel.orderId.postValue(it.responseBody!!.data!!.orderId.toString())
                mBinding.tvOrderDate.text = it.responseBody!!.data!!.orderDate
                mBinding.tvOrderNumber.text = it.responseBody!!.data!!.orderNumber
                mBinding.tvPayType.text = it.responseBody!!.data!!.paymentMethod
                mBinding.tvOrderPayStatus.text = it.responseBody!!.data!!.paymentStatus
                mBinding.tvStatus.text = it.responseBody!!.data!!.orderStatus
                mBinding.tvForAllOrder.text = it.responseBody!!.data!!.totalAmount+" "+getString(R.string.rs)
                mBinding.tvClientName.text = it.responseBody!!.data!!.customerName
                mBinding.tvClientPhone.text = it.responseBody!!.data!!.customerPhone
                mBinding.tvAddressDetails.text = it.responseBody!!.data!!.locationName
                mBinding.rcOrders.run {
                    layoutManager = LinearLayoutManager(context)
                    adapter = OrderDetailsItemAdapter(
                        it.responseBody!!.data!!.orderItems,arg.type.toLowerCase(),
                        this@OrderDetailsFragment,
                        context
                    )
                }
            }
        })
    }

}