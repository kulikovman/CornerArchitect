package com.searcharchitect.one.ui.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.searcharchitect.common.utility.extension.sendEmail
import com.searcharchitect.one.R
import com.searcharchitect.one.base.BaseFragment
import com.searcharchitect.one.databinding.LoginFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<LoginFragmentBinding, LoginViewModel>() {

    override fun getLayoutId(): Int = R.layout.login_fragment

    override val viewModel: LoginViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.hideKeyboard = ::hideKeyboard
        viewModel.sendEmail = { email -> context?.sendEmail(email) }
    }

}