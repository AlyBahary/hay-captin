package com.beetleware.hayatiDeliveryMan.ui.bottom_sheet.photo_bottom_sheet

import com.beetleware.hayatiDeliveryMan.ui.base.BaseView


interface PhotoBottomSheetView  : BaseView {


 fun checkCameraPermission()
 fun checkStoragePermission()

}