package com.corner.searcharchitect.ui.loading_error

import androidx.fragment.app.viewModels
import com.corner.searcharchitect.R
import com.corner.searcharchitect.base.BaseFragment
import com.corner.searcharchitect.databinding.LoadingErrorFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoadingErrorFragment : BaseFragment<LoadingErrorFragmentBinding, LoadingErrorViewModel>() {

    override fun getLayoutId(): Int = R.layout.loading_error_fragment

    override val viewModel: LoadingErrorViewModel by viewModels()

}