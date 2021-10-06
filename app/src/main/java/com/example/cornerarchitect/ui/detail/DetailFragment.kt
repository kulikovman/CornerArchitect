package com.example.cornerarchitect.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.cornerarchitect.R
import com.example.cornerarchitect.base.BaseFragment
import com.example.cornerarchitect.databinding.DetailFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<DetailFragmentBinding, DetailViewModel>() {

    override fun getLayoutId(): Int = R.layout.detail_fragment

    override val viewModel: DetailViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.callPhoneNumber = ::callPhoneNumber
        viewModel.sendEmail = ::sendEmail
        viewModel.openInstagram = ::openInstagram
        viewModel.openFacebook = ::openFacebook
        viewModel.openVk = ::openVk
    }

    private fun callPhoneNumber(phone: String) {
        val uri: Uri = Uri.parse("tel:$phone")
        val callIntent = Intent(Intent.ACTION_DIAL, uri)
        requireContext().startActivity(callIntent)
    }

    private fun sendEmail(email: String) {

    }

    private fun openInstagram(link: String) {

    }

    private fun openFacebook(link: String) {

    }

    private fun openVk(link: String) {

    }

}