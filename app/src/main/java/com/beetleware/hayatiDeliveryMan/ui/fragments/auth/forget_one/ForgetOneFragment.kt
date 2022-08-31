package com.beetleware.hayatiDeliveryMan.ui.fragments.auth.forget_one

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.beetleware.hayatiDeliveryMan.R
import com.beetleware.hayatiDeliveryMan.common.extensions.toast
import com.beetleware.hayatiDeliveryMan.data.network.model.CodesResponse
import com.beetleware.hayatiDeliveryMan.databinding.FragmentForgetOneBinding
import com.beetleware.hayatiDeliveryMan.ui.base.BaseFragment
import java.util.ArrayList

class ForgetOneFragment : ForgetOneView, BaseFragment<ForgetOneViewModel, FragmentForgetOneBinding>(
    ForgetOneViewModel::class.java) {

    private var codeObject = ArrayList<CodesResponse.Data>()
    var codeId = ""

    override fun getLayoutRes() = R.layout.fragment_forget_one

    override fun initViewModel(viewModel: ForgetOneViewModel) {
        mBinding.viewModel=viewModel
    }

    override fun observeLiveDatas() {
        super.observeLiveDatas()

        viewModel.isDataValid.observe(this, Observer {
            viewModel.phoneNumber = mBinding.tvPhoneNumber.text.toString()

            codeObject.forEach {
                if (it.code.toString() == mBinding.spSpinner.text.toString()) {
                    codeId = it.id.toString()
                }
            }
            viewModel.code=codeId

            if (it) {
                viewModel.isDataValid.value = false
                forgetPassword(codeId,mBinding.tvPhoneNumber.text.toString())
            }
        })
    }

    override fun init(savedInstanceState: Bundle?) {
        mBinding.spSpinner.setText("966")
        initCodes()
        mBinding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun initCodes(){
        viewModel.getCodes().observe(this, Observer {
            if(it.isResponseSuccessful){
                codeObject =it.responseBody!!.data!!.data
                val chronicDiseases = ArrayList<String>()
                codeObject.forEach {
                    chronicDiseases.add(it.code.toString())
                }

                val chronicArray = ArrayAdapter(
                    requireContext(), android.R.layout.simple_dropdown_item_1line,
                    chronicDiseases
                )

                mBinding.spSpinner.setAdapter(chronicArray)
            }
        })
    }

    override fun forgetPassword(codeId: String, phoneNumber: String) {
        viewModel.forgetPassword(codeId,phoneNumber,"0").observe(this, Observer {
            if(it.isResponseSuccessful){
                toast(it.responseBody!!.data)
                findNavController().navigate(R.id.action_forgetOneFragment_to_forgetTwoFragment)
            }
        })
    }

}