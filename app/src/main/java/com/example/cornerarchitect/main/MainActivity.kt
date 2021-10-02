package com.example.cornerarchitect.main

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.animation.doOnEnd
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.cornerarchitect.R
import com.example.cornerarchitect.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.vm = viewModel

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