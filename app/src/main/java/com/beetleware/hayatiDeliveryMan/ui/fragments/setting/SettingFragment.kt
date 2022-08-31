package com.beetleware.hayatiDeliveryMan.ui.fragments.setting

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.beetleware.hayatiDeliveryMan.R
import com.beetleware.hayatiDeliveryMan.common.extensions.goToActivity
import com.beetleware.hayatiDeliveryMan.databinding.FragmentSettingBinding
import com.beetleware.hayatiDeliveryMan.ui.activities.splash.SplashActivity
import com.beetleware.hayatiDeliveryMan.ui.base.BaseFragment

class SettingFragment : SettingView, BaseFragment<SettingViewModel, FragmentSettingBinding>(
    SettingViewModel::class.java) {

    override fun getLayoutRes() = R.layout.fragment_setting

    override fun initViewModel(viewModel: SettingViewModel) {
        mBinding.viewModel=viewModel
    }

    override fun init(savedInstanceState: Bundle?) {
        mBinding.swReceiveNotification.isChecked = viewModel.receiveNotification().value!!
        mBinding.swSoundNotification.isChecked = viewModel.soundNotification().value!!
        mBinding.btnForgetPassword.setOnClickListener {
            findNavController().navigate(R.id.action_settingFragment_to_changePasswordFragment)
        }
        mBinding.swSoundNotification.setOnClickListener {
            updateNotificationSetting(viewModel.receiveNotification().value!!,!viewModel.soundNotification().value!!)
        }
        mBinding.swReceiveNotification.setOnClickListener {
            updateNotificationSetting(!viewModel.receiveNotification().value!!,viewModel.receiveNotification().value!!)
        }
        initViews()
        mBinding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

    }
    override fun initViews(){
        if(viewModel.getLanguage() == 0){
            mBinding.swLanguage.isChecked=false
            mBinding.tvActive.text ="English Language"
        }else{
            mBinding.swLanguage.isChecked=true
            mBinding.tvActive.text ="اللغه العربيه"
        }
        mBinding.swLanguage.setOnClickListener {
            if(!mBinding.swLanguage.isChecked){
                viewModel.changeLang(0)
                goToActivity(SplashActivity::class.java)
                requireActivity().finish()
                mBinding.swLanguage.isChecked=false
            }else{
                viewModel.changeLang(1)
                goToActivity(SplashActivity::class.java)
                requireActivity().finish()
                mBinding.swLanguage.isChecked=true
            }
        }

    }

    override fun updateNotificationSetting(notificationReceive:Boolean,notificationSound:Boolean){
        viewModel.updateNotification(notificationReceive, notificationSound).observe(this, Observer {
            if(it.isResponseSuccessful){

            }
        })
    }
}