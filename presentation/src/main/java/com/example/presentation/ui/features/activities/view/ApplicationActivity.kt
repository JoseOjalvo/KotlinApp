package com.example.presentation.ui.features.activities.view

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import com.airbnb.lottie.LottieAnimationView
import com.example.presentation.R
import com.example.presentation.databinding.SplashScreenBinding

/**
 * Created by joseojalvo on 2020-08-05
 */
class ApplicationActivity : BaseActivity<SplashScreenBinding>() {

// =================================================================================================
// Config
// =================================================================================================

    /**
     * Creates a view binding instance with the layout of the current activity
     */
    override fun setBinding(inflater: LayoutInflater): SplashScreenBinding {
        return SplashScreenBinding.inflate(inflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSplashListener()
    }

    /**
     * Initializes the splash animation
     */
    override fun onResume() {
        super.onResume()
        binding.splash.setAnimation("splash.json")
        binding.splash.playAnimation()
    }

// =================================================================================================
// Initialization methods
// =================================================================================================

    /**
     * Creates a listener for the splash animation status
     */
    private fun initSplashListener() {
        binding.splash.addAnimatorListener(object : Animator.AnimatorListener {
                override fun onAnimationRepeat(animation: Animator?) {
                }

                override fun onAnimationEnd(animation: Animator?) {
                    startActivity(
                        Intent(
                            this@ApplicationActivity,
                            MainActivity::class.java
                        )
                    )
                }

                override fun onAnimationCancel(animation: Animator?) {
                }

                override fun onAnimationStart(animation: Animator?) {
                }
            })
    }
}