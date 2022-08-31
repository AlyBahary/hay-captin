package com.beetleware.hayatiDeliveryMan.ui.bottom_sheet.filter_orders

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.beetleware.hayatiDeliveryMan.R
import com.beetleware.hayatiDeliveryMan.databinding.BottomSheetFilterOrderBinding
import com.beetleware.hayatiDeliveryMan.ui.base.BaseBottomSheetDialog
import com.beetleware.hayatiDeliveryMan.ui.fragments.orders.OrdersViewModel

class FilterOrdersBottomSheet : FilterOrdersView,
    BaseBottomSheetDialog<OrdersViewModel, BottomSheetFilterOrderBinding>(OrdersViewModel::class.java) {

    override fun getLayoutRes() = R.layout.bottom_sheet_filter_order

    override fun initViewModel(viewModel: OrdersViewModel) {
        mBinding.viewModel = viewModel
    }

    override fun init(savedInstanceState: Bundle?) {
        mBinding.tvAllOrders.run {
            setBackgroundColor(ContextCompat.getColor(requireContext(),android.R.color.black))
            mBinding.tvAllOrders.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
        }
        mBinding.right.run {
            visibility = View.VISIBLE
            bringToFront()
        }
        mBinding.close.setOnClickListener {
            dismissAllowingStateLoss()
        }
        mBinding.containerForAllOrder.setOnClickListener {
            mBinding.tvAllOrders.run {
                setBackgroundColor(ContextCompat.getColor(requireContext(), android.R.color.black))
                mBinding.tvAllOrders.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.white
                    )
                )
            }
            mBinding.right.run {
                visibility = View.VISIBLE
                bringToFront()
            }
            viewModel.type.postValue("3")
            mBinding.tvCurrentOrders.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            mBinding.tvCurrentOrders.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    android.R.color.black
                )
            )
            mBinding.tvPreviousOrder.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            mBinding.tvPreviousOrder.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    android.R.color.black
                )
            )
            mBinding.tvCancelledOrder.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            mBinding.tvCancelledOrder.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    android.R.color.black
                )
            )
            mBinding.right2.visibility = View.GONE
            mBinding.right3.visibility = View.GONE
            mBinding.right4.visibility = View.GONE
            viewModel.typeBoolean.postValue(true)
        }
        mBinding.containerForCurrentOrders.setOnClickListener {
            mBinding.tvCurrentOrders.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    android.R.color.black
                )
            )
            mBinding.tvCurrentOrders.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            mBinding.right2.visibility = View.VISIBLE
            mBinding.right2.bringToFront()
            viewModel.type.postValue("0")

            mBinding.tvAllOrders.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            mBinding.tvAllOrders.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    android.R.color.black
                )
            )
            mBinding.tvPreviousOrder.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            mBinding.tvPreviousOrder.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    android.R.color.black
                )
            )
            mBinding.tvCancelledOrder.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            mBinding.tvCancelledOrder.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    android.R.color.black
                )
            )
            mBinding.right.visibility = View.GONE
            mBinding.right3.visibility = View.GONE
            mBinding.right4.visibility = View.GONE
            viewModel.typeBoolean.postValue(true)

        }
        mBinding.containerForPreviousOrder.setOnClickListener {
            mBinding.tvPreviousOrder.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    android.R.color.black
                )
            )
            mBinding.tvPreviousOrder.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            mBinding.right3.visibility = View.VISIBLE
            mBinding.right3.bringToFront()
            viewModel.type.postValue("1")

            mBinding.tvAllOrders.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            mBinding.tvAllOrders.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    android.R.color.black
                )
            )
            mBinding.tvCurrentOrders.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            mBinding.tvCurrentOrders.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    android.R.color.black
                )
            )
            mBinding.tvCancelledOrder.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            mBinding.tvCancelledOrder.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    android.R.color.black
                )
            )
            mBinding.right.visibility = View.GONE
            mBinding.right2.visibility = View.GONE
            mBinding.right4.visibility = View.GONE
            viewModel.typeBoolean.postValue(true)
        }
        mBinding.containerForCancelledOrders.setOnClickListener {
            mBinding.tvCancelledOrder.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    android.R.color.black
                )
            )
            mBinding.tvCancelledOrder.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            mBinding.right4.visibility = View.VISIBLE
            mBinding.right4.bringToFront()
            viewModel.type.postValue("2")
            mBinding.tvAllOrders.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            mBinding.tvAllOrders.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    android.R.color.black
                )
            )
            mBinding.tvCurrentOrders.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            mBinding.tvCurrentOrders.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    android.R.color.black
                )
            )
            mBinding.tvPreviousOrder.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            mBinding.tvPreviousOrder.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    android.R.color.black
                )
            )
            mBinding.right.visibility = View.GONE
            mBinding.right2.visibility = View.GONE
            mBinding.right3.visibility = View.GONE
            viewModel.typeBoolean.postValue(true)
        }
    }

    override fun openCamera() {


    }

    override fun openGallery() {

    }
}