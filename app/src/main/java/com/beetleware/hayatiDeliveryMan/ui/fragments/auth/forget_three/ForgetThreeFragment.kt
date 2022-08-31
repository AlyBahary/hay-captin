package com.beetleware.hayatiDeliveryMan.ui.fragments.auth.forget_three

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.beetleware.hayatiDeliveryMan.R
import com.beetleware.hayatiDeliveryMan.databinding.FragmentForgetThreeBinding
import com.beetleware.hayatiDeliveryMan.ui.base.BaseFragment
import com.beetleware.hayatiDeliveryMan.ui.fragments.auth.forget_one.ForgetOneViewModel

class ForgetThreeFragment : ForgetThreeView, BaseFragment<ForgetOneViewModel, FragmentForgetThreeBinding>(
    ForgetOneViewModel::class.java){


    override fun getLayoutRes() = R.layout.fragment_forget_three

    override fun initViewModel(viewModel: ForgetOneViewModel) {
        mBinding.viewModel=viewModel
    }

    override fun observeLiveDatas() {
        super.observeLiveDatas()

        viewModel.isDataValid.observe(this, Observer {
            if (it) {
                viewModel.isDataValid.value = false
                changePassword(viewModel.code,viewModel.phoneNumber,mBinding.tvPassword.text.toString())
            }
        })
    }
    override fun init(savedInstanceState: Bundle?) {
        mBinding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun changePassword(code: String, phoneNumber: String, password: String) {
        viewModel.changePassword(code,phoneNumber,password).observe(this, Observer {
            if(it.isResponseSuccessful){
                findNavController().navigate(R.id.action_forgetThreeFragment_to_loginFragment)
            }
        })
    }
}