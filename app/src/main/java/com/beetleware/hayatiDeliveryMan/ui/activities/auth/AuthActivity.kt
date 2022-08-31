package com.beetleware.hayatiDeliveryMan.ui.activities.auth

import android.content.Intent
import android.os.Bundle
import com.beetleware.hayatiDeliveryMan.R
import com.beetleware.hayatiDeliveryMan.databinding.ActivityAuthBinding
import com.beetleware.hayatiDeliveryMan.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_auth.*


class AuthActivity : AuthView, BaseActivity<AuthViewModel, ActivityAuthBinding>(AuthViewModel::class.java) {

    override fun getLayoutRes()= R.layout.activity_auth

    override fun initViewModel(viewModel: AuthViewModel) {
        mBinding.viewModel=viewModel
    }

    override fun init(savedInstanceState: Bundle?) {

    }

    override fun observeLiveDatas() {

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        navHostFragment.childFragmentManager.fragments[0]
            .onActivityResult(requestCode, resultCode, data)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        navHostFragment.childFragmentManager.fragments[0]
            .onRequestPermissionsResult(requestCode, permissions, grantResults)

    }

}