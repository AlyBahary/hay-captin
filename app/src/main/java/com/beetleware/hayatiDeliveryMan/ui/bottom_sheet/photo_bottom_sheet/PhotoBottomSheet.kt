package com.beetleware.hayatiDeliveryMan.ui.bottom_sheet.photo_bottom_sheet

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.core.content.ContextCompat
import com.beetleware.hayatiDeliveryMan.R
import com.beetleware.hayatiDeliveryMan.common.Constants
import com.beetleware.hayatiDeliveryMan.common.utils.ImageManger
import com.beetleware.hayatiDeliveryMan.databinding.UpdateProfilePhotoBottomSheetBinding
import com.beetleware.hayatiDeliveryMan.ui.base.BaseBottomSheetDialog
import com.beetleware.hayatiDeliveryMan.ui.fragments.personal_profile.PersonalProfileViewModel

class PhotoBottomSheet : PhotoBottomSheetView, BaseBottomSheetDialog<PersonalProfileViewModel, UpdateProfilePhotoBottomSheetBinding>(PersonalProfileViewModel::class.java) {

    override fun getLayoutRes()= R.layout.update_profile_photo_bottom_sheet

    override fun init(savedInstanceState: Bundle?) {
         mBinding.viewModel=viewModel
    }

    override fun initViewModel(viewModel: PersonalProfileViewModel) {
        mBinding.containerForGallery.setOnClickListener { checkStoragePermission() }
        mBinding.containerForCamera.setOnClickListener { checkCameraPermission() }
    }

    override fun checkStoragePermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                activity?.requestPermissions(
                    arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                    Constants.REQUEST_STORAGE_PERMISSION_CODE
                )
        } else
            openGallery()
        dismissAllowingStateLoss()
    }

    override fun checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                activity?.requestPermissions(
                    arrayOf(android.Manifest.permission.CAMERA),
                    Constants.REQUEST_CAMERA_PERMISSION_CODE
                )
        } else
            openCamera()

        dismissAllowingStateLoss()
    }

    override fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
        activity?.startActivityForResult(intent, Constants.GALLERY_REQUEST_CODE)
    }

    override fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, ImageManger.save_image_in_provider(context))
        activity?.startActivityForResult(intent, Constants.CAMERA_REQUEST_CODE)
    }

}