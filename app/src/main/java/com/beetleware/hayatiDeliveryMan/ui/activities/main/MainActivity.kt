package com.beetleware.hayatiDeliveryMan.ui.activities.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.lifecycle.Observer
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.beetleware.hayatiDeliveryMan.R
import com.beetleware.hayatiDeliveryMan.databinding.ActivityMainBinding
import com.beetleware.hayatiDeliveryMan.ui.base.BaseActivity
import com.beetleware.hayatiDeliveryMan.ui.fragments.home.HomeFragment
import com.beetleware.hayatiDeliveryMan.ui.fragments.orders.OrdersFragment
import com.beetleware.hayatiDeliveryMan.ui.fragments.profile.ProfileFragment
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_main.*
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions


class MainActivity : MainView, EasyPermissions.PermissionCallbacks, BaseActivity<MainViewModel, ActivityMainBinding>(
    MainViewModel::class.java
) {


    override fun getLayoutRes()= R.layout.activity_main

    override fun observeLiveDatas() {

    }

    override fun initViewModel(viewModel: MainViewModel) {
        mBinding.viewModel=viewModel
    }

    override fun init(savedInstanceState: Bundle?) {
        updateFirebaseToken()
        val navController = findNavController(R.id.navHostFragment)
        native_bottom_nav.setupWithNavController(navController)
        mBinding.bottomNavigation.setNavigationChangeListener { _, position ->
            when (position) {
                0 -> mBinding.nativeBottomNav.selectedItemId = R.id.homeFragment
                1 -> mBinding.nativeBottomNav.selectedItemId = R.id.ordersFragment
                2 -> mBinding.nativeBottomNav.selectedItemId = R.id.profileFragment
            }
        }
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
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);

    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {

    }


    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this).build().show()
        }
    }


    override fun onSupportNavigateUp() =
        findNavController(this, R.id.navHostFragment).navigateUp()

    override fun onBackPressed() {
        if (navHostFragment!!.childFragmentManager.fragments[0] is HomeFragment) {
            super.onBackPressed()
        } else if (navHostFragment.childFragmentManager.fragments[0] is OrdersFragment
            || navHostFragment.childFragmentManager.fragments[0] is ProfileFragment) {
            bottom_navigation.setCurrentActiveItem(0)
        } else {
            super.onBackPressed()
        }
    }

    @SuppressLint("HardwareIds")
    override fun updateFirebaseToken(){
        FirebaseMessaging.getInstance().token.addOnFailureListener {
            Log.d("FIREBASE_FAIL", "updateFirebaseToken: ${it.localizedMessage}")
            Log.d("FIREBASE_FAIL", "updateFirebaseToken: ${it.cause}")
            Log.d("FIREBASE_FAIL", "updateFirebaseToken: ${it.message}")

        }
        FirebaseMessaging.getInstance().token.addOnCompleteListener {
            Log.d("FIREBASE", "updateFirebaseToken: ${it}")
            Log.d("FIREBASE", "updateFirebaseToken: ${it.result}")
            viewModel.updateFirebaseToken(it.result,Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)).observe(this, Observer {
//        viewModel.updateFirebaseToken("",Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)).observe(this, Observer {
                if(it.isResponseSuccessful){

                }
            })
        }

    }

}
