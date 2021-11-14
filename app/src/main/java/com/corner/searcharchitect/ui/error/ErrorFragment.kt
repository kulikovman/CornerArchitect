package com.corner.searcharchitect.ui.error

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.corner.searcharchitect.R
import com.corner.searcharchitect.base.BaseFragment
import com.corner.searcharchitect.databinding.ErrorFragmentBinding
import com.corner.searcharchitect.main.MainActivity
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