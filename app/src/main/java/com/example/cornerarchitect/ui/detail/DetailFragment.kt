package com.example.cornerarchitect.ui.detail

import android.content.ActivityNotFoundException
import android.content.ComponentName
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
        val uri = Uri.parse("mailto:$email")
        val intent = Intent(Intent.ACTION_SENDTO, uri)

        startActivity(Intent.createChooser(intent, null))
    }

    private fun openInstagram(profileId: String) {
        val appUri = Uri.parse("http://instagram.com/_u/$profileId")
        val appIntent = Intent(Intent.ACTION_VIEW, appUri).apply {
            component = ComponentName(
                "com.instagram.android",
                "com.instagram.android.activity.UrlHandlerActivity"
            )
        }

        val browserUri = Uri.parse("http://instagram.com/$profileId")
        val browserIntent = Intent(Intent.ACTION_VIEW, browserUri)

        try {
            startActivity(appIntent)
        } catch (e: ActivityNotFoundException) {
            startActivity(browserIntent)
        }
    }

    private fun openFacebook(profileId: String) {
        val uri = Uri.parse("https://www.facebook.com/n/?$profileId")
        val intent = Intent(Intent.ACTION_VIEW, uri)

        startActivity(intent)
    }

    private fun openVk(profileId: String) {
        val uri = Uri.parse("http://vk.com/$profileId")
        val intent = Intent(Intent.ACTION_VIEW, uri)

        startActivity(intent)
    }

}