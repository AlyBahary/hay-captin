package com.beetleware.hayatiDeliveryMan.ui.fragments.auth.forget_two

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.beetleware.hayatiDeliveryMan.R
import com.beetleware.hayatiDeliveryMan.common.extensions.toast
import com.beetleware.hayatiDeliveryMan.databinding.FragmentForgetTwoBinding
import com.beetleware.hayatiDeliveryMan.ui.base.BaseBottomSheetDialog
import com.beetleware.hayatiDeliveryMan.ui.fragments.auth.forget_one.ForgetOneViewModel

class ForgetTwoFragment : ForgetTwoView,
    BaseBottomSheetDialog<ForgetOneViewModel, FragmentForgetTwoBinding>(ForgetOneViewModel::class.java) {

    override fun getLayoutRes() = R.layout.fragment_forget_two

    override fun initViewModel(viewModel: ForgetOneViewModel) {
        mBinding.viewModel = viewModel
    }

    override fun init(savedInstanceState: Bundle?) {

        mBinding.containerForDown.setOnClickListener {
            if (viewModel.resendObserver.value!!) {
                viewModel.sendOtp(viewModel.phoneNumber, viewModel.code, "0")
                    .observe(this, Observer {
                        if (it.isResponseSuccessful) {
                            mBinding.downCounter.visibility = View.VISIBLE
                        }
                    })
                val timer = object : CountDownTimer(30000, 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                        mBinding.downCounter.text = (millisUntilFinished / 1000).toString()
                    }

                    override fun onFinish() {
                        mBinding.downCounter.visibility = View.GONE
                        mBinding.containerForDown.setBackgroundResource(R.drawable.radius_button_pink)
                        try {
                            viewModel.resendObserver.postValue(true)
                            mBinding.tvResend.setTextColor(resources.getColor(R.color.white))
                            mBinding.tvResend.text = getString(R.string.resend_again)
                        } catch (e: Exception) {

                        }

                    }
                }
                timer.start()
                viewModel.resendObserver.postValue(false)
            }
        }
        val timer = object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                mBinding.downCounter.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                mBinding.downCounter.visibility = View.GONE
                mBinding.containerForDown.setBackgroundResource(R.drawable.radius_button_pink)
                try {
                    viewModel.resendObserver.postValue(true)
                    mBinding.tvResend.setTextColor(resources.getColor(R.color.white))
                    mBinding.tvResend.text = getString(R.string.resend_again)
                } catch (e: Exception) {

                }

            }
        }
        timer.start()


        viewModel.sendOtp(viewModel.phoneNumber, viewModel.code, "0").observe(this, Observer {
            if (it.isResponseSuccessful) {

            }
        })
        mBinding.closeVerification.setOnClickListener {
            dismissAllowingStateLoss()
        }
        mBinding.btnVerification.setOnClickListener {
            viewModel.checkCode(
                viewModel.code,
                viewModel.phoneNumber,
                mBinding.codePinView.text.toString()
            ).observe(this,
                Observer {
                    if (it.isResponseSuccessful) {
                        findNavController().navigate(R.id.action_forgetTwoFragment_to_forgetThreeFragment)
                    } else {
                        toast(getString(R.string.code_not_correct))
                    }
                })
        }
        mBinding.tvResendViaEmail.setOnClickListener {
            viewModel.sendOtp(viewModel.phoneNumber, viewModel.code, "1").observe(this,
                Observer {
                    if (it.isResponseSuccessful) {
                        toast(it.responseBody!!.successMessage)
                    }
                })
        }
    }

    override fun openCamera() {

    }

    override fun openGallery() {

    }
}