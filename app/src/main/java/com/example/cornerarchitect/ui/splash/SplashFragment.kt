package com.example.cornerarchitect.ui.splash

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import androidx.core.animation.doOnEnd
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.cornerarchitect.R
import com.example.cornerarchitect.base.BaseFragment
import com.example.cornerarchitect.databinding.SplashFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : BaseFragment<SplashFragmentBinding, SplashViewModel>() {

    override fun getLayoutId(): Int = R.layout.splash_fragment

    override val viewModel: SplashViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.animateSplashFadeOut = ::animateSplashFadeOut
    }

    private fun animateSplashFadeOut(splashShowingTime: Long, actionAfterEnd: () -> Unit) {
        lifecycleScope.launch {
            delay(splashShowingTime) // 900 - min time splash showing
            ObjectAnimator.ofFloat(binding.splashLogo, "alpha", 0f).apply {
                duration = 1400 // 1400 - alfa duration
                start()
            }.doOnEnd {
                actionAfterEnd.invoke()
            }
        }
    }

}