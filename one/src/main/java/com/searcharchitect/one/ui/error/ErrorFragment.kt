package com.searcharchitect.one.ui.error

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.searcharchitect.one.R
import com.searcharchitect.one.base.BaseFragment
import com.searcharchitect.one.databinding.ErrorFragmentBinding
import com.searcharchitect.one.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ErrorFragment : BaseFragment<ErrorFragmentBinding, ErrorViewModel>() {

    override fun getLayoutId(): Int = R.layout.error_fragment

    override val viewModel: ErrorViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.restartApp = ::restartApp
    }

    private fun restartApp() {
        activity?.apply {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }
    }

}