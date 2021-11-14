package com.corner.searcharchitect.ui.login

import androidx.fragment.app.viewModels
import com.corner.searcharchitect.R
import com.corner.searcharchitect.base.BaseFragment
import com.corner.searcharchitect.databinding.LoginFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<LoginFragmentBinding, LoginViewModel>() {

    override fun getLayoutId(): Int = R.layout.login_fragment

    override val viewModel: LoginViewModel by viewModels()

}