package com.example.cornerarchitect.ui.splash

import androidx.fragment.app.viewModels
import com.example.cornerarchitect.R
import com.example.cornerarchitect.base.BaseFragment
import com.example.cornerarchitect.databinding.SplashFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<SplashFragmentBinding, SplashViewModel>() {

    override fun getLayoutId(): Int = R.layout.splash_fragment

    override val viewModel: SplashViewModel by viewModels()

}