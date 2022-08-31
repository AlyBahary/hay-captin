package com.beetleware.hayatiDeliveryMan.ui.fragments.change_password

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.beetleware.hayatiDeliveryMan.R
import com.beetleware.hayatiDeliveryMan.common.extensions.confirmMsg
import com.beetleware.hayatiDeliveryMan.common.extensions.errorMsg
import com.beetleware.hayatiDeliveryMan.databinding.FragmentChangePasswordBinding
import com.beetleware.hayatiDeliveryMan.ui.base.BaseFragment

class ChangePasswordFragment : ChangePasswordView, BaseFragment<ChangePasswordViewModel, FragmentChangePasswordBinding>(
    ChangePasswordViewModel::class.java) {

    override fun getLayoutRes() = R.layout.fragment_change_password

    override fun initViewModel(viewModel: ChangePasswordViewModel) {
        mBinding.viewModel=viewModel
    }

    override fun init(savedInstanceState: Bundle?) {
        mBinding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
        mBinding.btnUpdatePassowrd.setOnClickListener {
            initChangePassword(mBinding.etOldPassword.text.toString(),mBinding.etNewPassword.text.toString(),mBinding.etConfirmPassword.text.toString())
        }
    }


    override fun initChangePassword(oldPassword:String,newPassword:String,confirmNewPassword:String){
        if(oldPassword.isNotEmpty() && newPassword.isNotEmpty() && confirmNewPassword.isNotEmpty()){
            viewModel.changePassword(oldPassword, newPassword, confirmNewPassword).observe(this,
                Observer {
                    if(it.isResponseSuccessful){
                        confirmMsg(getString(R.string.password_updated_successfully))
                        findNavController().navigateUp()
                    }
                })
        }else{
            errorMsg(R.string.please_enter_all_required_fields)
        }

    }
}