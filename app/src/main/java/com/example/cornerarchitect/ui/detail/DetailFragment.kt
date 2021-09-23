package com.example.cornerarchitect.ui.detail

import androidx.fragment.app.viewModels
import com.example.cornerarchitect.R
import com.example.cornerarchitect.base.BaseFragment
import com.example.cornerarchitect.databinding.DetailFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<DetailFragmentBinding, DetailViewModel>() {

    override fun getLayoutId(): Int = R.layout.detail_fragment

    override val viewModel: DetailViewModel by viewModels()

}