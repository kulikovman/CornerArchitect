package com.example.searcharchitect.ui.splash

import androidx.fragment.app.viewModels
import com.example.searcharchitect.R
import com.example.searcharchitect.base.BaseFragment
import com.example.searcharchitect.databinding.SplashFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<SplashFragmentBinding, SplashViewModel>() {

    override fun getLayoutId(): Int = R.layout.splash_fragment

    override val viewModel: SplashViewModel by viewModels()

}