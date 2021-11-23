package com.searcharchitect.one.ui.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
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
        viewModel.sendEmail = ::sendEmail
    }

    private fun sendEmail(email: String) {
        val uri = Uri.parse("mailto:$email")
        val intent = Intent(Intent.ACTION_SENDTO, uri)

        startActivity(Intent.createChooser(intent, null))
    }

}