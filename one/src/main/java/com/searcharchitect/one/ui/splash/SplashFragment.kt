package com.searcharchitect.one.ui.splash

import androidx.fragment.app.viewModels
import com.searcharchitect.one.R
import com.searcharchitect.one.base.BaseFragment
import com.searcharchitect.one.databinding.SplashFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<SplashFragmentBinding, SplashViewModel>() {

    override fun getLayoutId(): Int = R.layout.splash_fragment

    override val viewModel: SplashViewModel by viewModels()

}