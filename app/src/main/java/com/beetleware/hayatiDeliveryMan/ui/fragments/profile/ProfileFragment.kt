package com.beetleware.hayatiDeliveryMan.ui.fragments.profile

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.beetleware.hayatiDeliveryMan.R
import com.beetleware.hayatiDeliveryMan.R.string.terms_conditions
import com.beetleware.hayatiDeliveryMan.common.extensions.goToActivity
import com.beetleware.hayatiDeliveryMan.databinding.FragmentProfileBinding
import com.beetleware.hayatiDeliveryMan.ui.activities.auth.AuthActivity
import com.beetleware.hayatiDeliveryMan.ui.adapter.ProfileAdapter
import com.beetleware.hayatiDeliveryMan.ui.base.BaseFragment

class ProfileFragment : ProfileView , BaseFragment<ProfileViewModel,FragmentProfileBinding>(ProfileViewModel::class.java) {

    override fun getLayoutRes() = R.layout.fragment_profile

    override fun initViewModel(viewModel: ProfileViewModel) {
        mBinding.viewModel = viewModel
    }

    override fun init(savedInstanceState: Bundle?) {
        initProfile()
        viewModel.getProfile().observe(this, Observer {
            if(it.isResponseSuccessful){
                mBinding.tvProfileName.text = it.responseBody!!.data!!.name
                mBinding.tvProfilePhone.text = it.responseBody!!.data!!.phone
                viewModel.receiveNotification().postValue(it.responseBody!!.data!!.notificationReceive)
                viewModel.soundNotification().postValue(it.responseBody!!.data!!.notificationSound)
            }
        })
    }

    override fun initProfile() {
        val profile = ArrayList<String>()
        profile.add(getString(R.string.personal_profile))
        profile.add(getString(R.string.notification))
        profile.add(getString(R.string.Setting))
        profile.add(getString(terms_conditions))
        profile.add(getString(R.string.logout))
        mBinding.rcProfile.run {
            layoutManager = LinearLayoutManager(context)
            adapter = ProfileAdapter(profile, this@ProfileFragment, context)
        }
    }

    override fun openNested(position: Int) {
        if(position == 0){
            findNavController().navigate(R.id.action_profileFragment_to_personalProfileFragment)
        }else if(position ==1){
            findNavController().navigate(R.id.action_profileFragment_to_notificationFragment)
        }else if(position ==2){
            findNavController().navigate(R.id.action_profileFragment_to_settingFragment)
        }else if(position ==3){
            findNavController().navigate(R.id.action_profileFragment_to_termsAndConditionsFragment)
        }else if(position ==4){
            viewModel.signout().observe(this, Observer {
                if(it.isResponseSuccessful){
                    viewModel.removeUser()
                    goToActivity(AuthActivity::class.java)
                    requireActivity().finish()
                }
            })
        }
    }
}