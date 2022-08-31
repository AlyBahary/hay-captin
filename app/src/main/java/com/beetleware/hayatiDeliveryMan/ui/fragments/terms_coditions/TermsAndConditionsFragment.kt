package com.beetleware.hayatiDeliveryMan.ui.fragments.terms_coditions

import android.os.Bundle
import android.text.Html
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.beetleware.hayatiDeliveryMan.R
import com.beetleware.hayatiDeliveryMan.databinding.FragmentTermsConditionsBinding
import com.beetleware.hayatiDeliveryMan.ui.base.BaseFragment

class TermsAndConditionsFragment : TermsAndConditionsView, BaseFragment<TermsAndConditionsViewModel, FragmentTermsConditionsBinding>(
    TermsAndConditionsViewModel::class.java) {

    override fun getLayoutRes() = R.layout.fragment_terms_conditions


    override fun initViewModel(viewModel: TermsAndConditionsViewModel) {
        mBinding.viewModel=viewModel
    }

    override fun init(savedInstanceState: Bundle?) {
        mBinding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
        viewModel.getTermsAndConditions().observe(this, Observer {
            if(it.isResponseSuccessful){
                mBinding.tvTermsConditions.text = Html.fromHtml(it.responseBody!!.data!!.value)
            }
        })
    }
}