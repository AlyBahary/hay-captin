package com.beetleware.hayatiDeliveryMan.ui.fragments.auth.login

import android.os.Bundle
import android.provider.Settings
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.beetleware.hayatiDeliveryMan.R
import com.beetleware.hayatiDeliveryMan.databinding.FragmentLoginBinding
import com.beetleware.hayatiDeliveryMan.ui.base.BaseFragment
//import com.google.firebase.iid.FirebaseInstanceId

class LoginFragment : LoginView, BaseFragment<LoginViewModel, FragmentLoginBinding>(LoginViewModel::class.java){

    override fun getLayoutRes() = R.layout.fragment_login

    override fun initViewModel(viewModel: LoginViewModel) {
        mBinding.viewModel=viewModel
    }

    override fun init(savedInstanceState: Bundle?) {
        mBinding.tvForgetPassword.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_forgetOneFragment)
        }
        mBinding.btnLogin.setOnClickListener {
            viewModel.login(mBinding.etData.text.toString(),mBinding.etpassword.text.toString(),
                Settings.Secure.getString(requireContext().contentResolver, Settings.Secure.ANDROID_ID),"hadgjkhajfhkasjhkjashfkjshfjkaf").observe(this, Observer {
                if(it.isResponseSuccessful){
                    viewModel.storeUser(it.responseBody!!.data!!)
                    findNavController().navigate(R.id.action_loginFragment_to_mainActivity)
                }
            })
        }
    }



}