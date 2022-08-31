package com.beetleware.hayatiDeliveryMan.ui.activities.splash

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.beetleware.hayatiDeliveryMan.R
import com.beetleware.hayatiDeliveryMan.common.extensions.goToActivity
import com.beetleware.hayatiDeliveryMan.databinding.ActivitySplashBinding
import com.beetleware.hayatiDeliveryMan.ui.activities.auth.AuthActivity
import com.beetleware.hayatiDeliveryMan.ui.activities.main.MainActivity
import com.beetleware.hayatiDeliveryMan.ui.base.BaseActivity

class SplashActivity : SplashView, BaseActivity<SplashViewModel, ActivitySplashBinding>(
    SplashViewModel::class.java){

    override fun getLayoutRes()= R.layout.activity_splash

    override fun init(savedInstanceState: Bundle?) {
        setSplashAnimation()
    }

    override fun observeLiveDatas() {

    }

    override fun initViewModel(viewModel: SplashViewModel) {
        mBinding.viewModel=viewModel
    }

    private fun setSplashAnimation() {
        val fadeInAnim = AnimationUtils.loadAnimation(this, R.anim.fadein_splash_time)
        mBinding.logo.startAnimation(fadeInAnim)
        fadeInAnim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                if (viewModel.isLoggedin()){
                    goToActivity(MainActivity::class.java)
                }
                else
                    goToActivity(AuthActivity::class.java)
                finish()
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })
    }

}