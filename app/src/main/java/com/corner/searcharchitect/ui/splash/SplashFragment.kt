package com.corner.searcharchitect.ui.splash

import androidx.fragment.app.viewModels
import com.corner.searcharchitect.R
import com.corner.searcharchitect.base.BaseFragment
import com.corner.searcharchitect.databinding.SplashFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<SplashFragmentBinding, SplashViewModel>() {

    override fun getLayoutId(): Int = R.layout.splash_fragment

    override val viewModel: SplashViewModel by viewModels()

}