package com.beetleware.hayatiDeliveryMan.ui.fragments.orders

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.beetleware.hayatiDeliveryMan.R
import com.beetleware.hayatiDeliveryMan.common.Constants
import com.beetleware.hayatiDeliveryMan.databinding.FragmentOrdersBinding
import com.beetleware.hayatiDeliveryMan.ui.adapter.OrderPagedListAdapter
import com.beetleware.hayatiDeliveryMan.ui.adapter.OrderSearchAdapter
import com.beetleware.hayatiDeliveryMan.ui.base.BaseFragment
import com.beetleware.hayatiDeliveryMan.ui.bottom_sheet.filter_orders.FilterOrdersBottomSheet

class OrdersFragment : OrdersView, BaseFragment<OrdersViewModel, FragmentOrdersBinding>(
    OrdersViewModel::class.java
) {

    private var type: String = Constants.ORDER
    override fun getLayoutRes() = R.layout.fragment_orders

    override fun initViewModel(viewModel: OrdersViewModel) {
        mBinding.viewModel = viewModel
    }

    override fun init(savedInstanceState: Bundle?) {
        mBinding.imgNotification.setOnClickListener {
            findNavController().navigate(R.id.action_ordersFragment_to_notificationFragment)
        }
        viewModel.typeBoolean.observe(this, Observer {
            if (it) {
                viewModel.filterOrders(viewModel.type.value!!,type.toLowerCase()).observe(this, Observer {
                    if (it.isResponseSuccessful) {
                        mBinding.rcOrders.run {
                            layoutManager = LinearLayoutManager(context)
                            adapter =
                                OrderSearchAdapter(
                                    it.responseBody!!.data!!,
                                    type,
                                    this@OrdersFragment,
                                    context
                                )
                            adapter!!.notifyDataSetChanged()
                        }
                        viewModel.typeBoolean.postValue(false)
                    }
                })
            }
        })

        mBinding.imgFilter.setOnClickListener {
            val filter = FilterOrdersBottomSheet()
            filter.show(childFragmentManager, "")
        }
        viewModel.orders().observe(this, Observer {
            if (it.isResponseSuccessful) {
                initOrders()
            }
        })
        mBinding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(
                s: CharSequence?, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                if (s.isNotEmpty()) {
                    viewModel.isLoading.postValue(false)
                    viewModel.searchOrders(mBinding.etSearch.text.toString(),type.toLowerCase())
                        .observe(this@OrdersFragment, Observer {
                            if (it.isResponseSuccessful) {
                                mBinding.rcOrders.run {
                                    layoutManager = LinearLayoutManager(context)
                                    adapter = OrderSearchAdapter(
                                        it.responseBody!!.data!!, type,
                                        this@OrdersFragment,
                                        context
                                    )
                                    adapter!!.notifyDataSetChanged()
                                }
                            }
                        })
                }
            }
        })

        mBinding.ordersTv.setOnClickListener {
            type = Constants.ORDER
            initOrders()

            mBinding.ordersTv.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            mBinding.ordersTv.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.radius_statics)

            mBinding.subscriptionTv.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.colorPrimary
                )
            )
            mBinding.subscriptionTv.background = ContextCompat.getDrawable(
                requireContext(),
                R.drawable.transition_background_drawable
            )

        }
        mBinding.subscriptionTv.setOnClickListener {
            type = Constants.SUBSCRIPTION

            initOrders()
            mBinding.subscriptionTv.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            mBinding.subscriptionTv.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.radius_statics)

            mBinding.ordersTv.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.colorPrimary
                )
            )
            mBinding.ordersTv.background = ContextCompat.getDrawable(
                requireContext(),
                R.drawable.transition_background_drawable
            )


        }
    }


    override fun initOrders() {
        viewModel.isLoading.postValue(true)

        mBinding.rcOrders.layoutManager = LinearLayoutManager(context)
        val notificationsPagingAdapter = OrderPagedListAdapter(this, type, requireContext())
        mBinding.rcOrders.adapter = notificationsPagingAdapter

        if (type == Constants.ORDER) {
            viewModel.getOrders().observe(this, Observer {
                viewModel.isLoading.postValue(false)
                notificationsPagingAdapter.setList(it)
            })
        } else {

            viewModel.getSubscriptionOrders().observe(this, Observer {
                viewModel.isLoading.postValue(false)
                notificationsPagingAdapter.setList(it)
            })
        }
        viewModel.getOrdersErrorResponse().observe(this, Observer {
            viewModel.isLoading.postValue(false)

            if (!it?.isResponseSuccessful!!)
                handleErrorResponse(it)
        })
    }

    override fun openOrderDetails(orderId: String) {
        val action = OrdersFragmentDirections.actionOrdersFragmentToOrderDetailsFragment(orderId,type.toLowerCase())
        findNavController().navigate(action)
    }
}