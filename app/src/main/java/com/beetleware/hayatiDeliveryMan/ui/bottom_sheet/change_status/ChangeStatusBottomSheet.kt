package com.beetleware.hayatiDeliveryMan.ui.bottom_sheet.change_status

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.beetleware.hayatiDeliveryMan.R
import com.beetleware.hayatiDeliveryMan.data.network.model.OrderStatus
import com.beetleware.hayatiDeliveryMan.databinding.BottomSheetOrderStatusBinding
import com.beetleware.hayatiDeliveryMan.ui.adapter.UpdateStatusAdapter
import com.beetleware.hayatiDeliveryMan.ui.base.BaseBottomSheetDialog
import com.beetleware.hayatiDeliveryMan.ui.fragments.order_details.OrderDetailsViewModel

class ChangeStatusBottomSheet : ChangeStatusView, BaseBottomSheetDialog<OrderDetailsViewModel, BottomSheetOrderStatusBinding>(
    OrderDetailsViewModel::class.java
) {

    var sendStatus :String =""

    override fun getLayoutRes() = R.layout.bottom_sheet_order_status


    override fun initViewModel(viewModel: OrderDetailsViewModel) {
        mBinding.viewModel=viewModel
    }

    override fun init(savedInstanceState: Bundle?) {
        mBinding.close.setOnClickListener {
            dismissAllowingStateLoss()
        }

//        mBinding.btnUpdate.setOnClickListener {
//            try {
//                viewModel.updateOrder(viewModel.orderId.value!!,sendStatus).observe(this, Observer {
//                    if(it.isResponseSuccessful){
//                        toast(getString(R.string.order_done_successfully))
//                        dismissAllowingStateLoss()
//                    }else{
//                        dismissAllowingStateLoss()
//                    }
//                })
//            }catch (e:Exception){
//                errorMsg(getString(R.string.choose_order_status))
//            }
//        }
        val status = ArrayList<OrderStatus>()
        val statusAccept = OrderStatus(getString(R.string.accept_status),R.drawable.ic_vespa)
        val statusPreOne = OrderStatus(getString(R.string.arrived),R.drawable.ic_vespa)
        val statusOne = OrderStatus(getString(R.string.delivery_done),R.drawable.ic_vespa)
//        val statusTwo = OrderStatus(getString(R.string.exit_order),R.drawable.ic_vespa)

        status.add(statusAccept)
        status.add(statusPreOne)
        status.add(statusOne)
//        status.add(statusTwo)
        mBinding.rcUpdateStatus.run {
            layoutManager = LinearLayoutManager(context)
            adapter = UpdateStatusAdapter(status, this@ChangeStatusBottomSheet, context)
        }

    }

    override fun openCamera() {

    }

    override fun openGallery() {

    }

    override fun changeStatus(status: String) {
        when (status) {
            "0" -> {
                sendStatus = "accept"
            }
            "1" -> {
                sendStatus = "arrival"
            }
            "2" -> {
                sendStatus = "delivered"
            }
        }
    }
}