package com.beetleware.hayatiDeliveryMan.ui.fragments.personal_profile

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.beetleware.hayatiDeliveryMan.R
import com.beetleware.hayatiDeliveryMan.common.Constants
import com.beetleware.hayatiDeliveryMan.common.extensions.confirmMsg
import com.beetleware.hayatiDeliveryMan.common.extensions.loadImg
import com.beetleware.hayatiDeliveryMan.common.utils.ImageManger
import com.beetleware.hayatiDeliveryMan.databinding.FragmentPersonalProfileBinding
import com.beetleware.hayatiDeliveryMan.ui.base.BaseFragment
import com.beetleware.hayatiDeliveryMan.ui.bottom_sheet.photo_bottom_sheet.PhotoBottomSheet
import id.zelory.compressor.Compressor
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import pub.devrel.easypermissions.EasyPermissions
import java.io.File
import java.net.URLConnection

class PersonalProfileFragment : PersonalProfileView, BaseFragment<PersonalProfileViewModel, FragmentPersonalProfileBinding>(
    PersonalProfileViewModel::class.java) {

    var gender= ""
    private var mediaPart: MultipartBody.Part? = null

    override fun getLayoutRes() = R.layout.fragment_personal_profile

    override fun initViewModel(viewModel: PersonalProfileViewModel) {
        mBinding.viewModel=viewModel
    }

    override fun init(savedInstanceState: Bundle?) {
        initViews()
    }

    override fun initViews(){
        mBinding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
        mBinding.changeProfileImage.setOnClickListener {
            val x = PhotoBottomSheet()
            x.show(childFragmentManager, "")
        }
        viewModel.getProfile().observe(this, Observer {
            if(it.isResponseSuccessful){
                mBinding.etName.setText(it.responseBody!!.data!!.name)
                mBinding.etEmail.setText(it.responseBody!!.data!!.email)
                mBinding.etAddress.setText(it.responseBody!!.data!!.address)
                mBinding.etPhoneNumber.setText(it.responseBody!!.data!!.phone)
                mBinding.imgProfile.loadImg(it.responseBody!!.data!!.image,R.drawable.ic_user_profile)

            }
        })

        mBinding.btnUpdateProfile.setOnClickListener {
            val firstName = mBinding.etName.text!!.split(" ")[0]
            val lastName =mBinding.etName.text!!.substring(firstName.length,mBinding.etName.length())
            viewModel.updateProfile(firstName,lastName,mBinding.etEmail.text.toString(),mBinding.etAddress.text.toString(),mBinding.etPhoneNumber.text.toString()).observe(this,
                Observer {
                    if(it.isResponseSuccessful){
                        confirmMsg(getString(R.string.profile_updated_successfully))
                    }
                })
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (requestCode == Constants.REQUEST_CAMERA_PERMISSION_CODE)
                openCamera()
            else if (requestCode == Constants.REQUEST_STORAGE_PERMISSION_CODE)
                openGallery()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            Constants.GALLERY_REQUEST_CODE -> {
                if (resultCode == Activity.RESULT_OK) {
                    val selectedImage: Uri = data?.data!!
                    val selectedMediaPath: String

                    val cursor = requireActivity().contentResolver?.query(
                        selectedImage, null,
                        null, null, null
                    )

                    selectedMediaPath = if (cursor == null)
                        selectedImage.path!!
                    else {
                        cursor.moveToFirst()
                        val idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
                        cursor.getString(idx)
                    }
                    cursor?.close()

                    val userPhotoFile = File(selectedMediaPath)
                    val compressedImageFile = Compressor(requireContext()).compressToFile(userPhotoFile)

                    val userFileRequest = RequestBody.create(
                        MediaType
                            .parse(URLConnection.guessContentTypeFromName(compressedImageFile.name)),
                        compressedImageFile
                    )
                    mediaPart = try {
                        MultipartBody.Part.createFormData(
                            "image",
                            compressedImageFile.name,
                            userFileRequest
                        )
                    } catch (e: IllegalArgumentException) {
                        MultipartBody.Part.createFormData(
                            "image",
                            "not_supported_file_name",
                            userFileRequest
                        )
                    }

                    viewModel.uploadChatImage(mediaPart).observe(this, androidx.lifecycle.Observer {
                        if(it.isResponseSuccessful){
                            mBinding.imgProfile.loadImg(compressedImageFile,R.drawable.ic_user_profile)
                            confirmMsg(getString(R.string.photo_updated_successfully))
                            viewModel.isLoading.postValue(false)
                        }else{
                            viewModel.isLoading.postValue(false)
                        }
                    })
                }
            }

            Constants.CAMERA_REQUEST_CODE -> {
                if (resultCode == Activity.RESULT_OK) {

                    val userPhotoFile = ImageManger.get_saved_image(requireContext())
                    val compressedImageFile = Compressor(requireContext()).compressToFile(userPhotoFile)

                    val userFileRequest = RequestBody.create(
                        MediaType
                            .parse(URLConnection.guessContentTypeFromName(compressedImageFile.name)),
                        compressedImageFile
                    )


                    mediaPart = try {
                        MultipartBody.Part.createFormData(
                            "image",
                            compressedImageFile.name,
                            userFileRequest
                        )
                    } catch (e: IllegalArgumentException) {
                        MultipartBody.Part.createFormData(
                            "image",
                            "not_supported_file_name",
                            userFileRequest
                        )
                    }
                    viewModel.uploadChatImage(mediaPart).observe(this, androidx.lifecycle.Observer {
                        if(it.isResponseSuccessful){
                            mBinding.imgProfile.loadImg(compressedImageFile,R.drawable.ic_user_profile)
                            confirmMsg(getString(R.string.photo_updated_successfully))
                            viewModel.isLoading.postValue(false)
                        }else{
                            viewModel.isLoading.postValue(false)
                        }
                    })
                }
            }
        }

    }




}