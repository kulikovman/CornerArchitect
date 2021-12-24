package com.searcharchitect.one.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.searcharchitect.common.utility.extension.*
import com.searcharchitect.one.R
import com.searcharchitect.one.base.BaseFragment
import com.searcharchitect.one.databinding.DetailFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<DetailFragmentBinding, DetailViewModel>() {

    override fun getLayoutId(): Int = R.layout.detail_fragment

    override val viewModel: DetailViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()

        viewModel.setProfilePhoto = ::setProfilePhoto
        viewModel.callPhoneNumber = { phone -> context?.callPhoneNumber(phone) }
        viewModel.sendEmail = { email -> context?.sendEmail(email) }
        viewModel.openTelegram = { profileId -> context?.openTelegram(profileId) }
        viewModel.openInstagram = { profileId -> context?.openInstagram(profileId) }
        viewModel.openFacebook = { profileId -> context?.openFacebook(profileId) }
        viewModel.openVk = { profileId -> context?.openVk(profileId) }
        viewModel.openLink = { link -> context?.openLink(link) }
    }

    private fun initToolbar() {
        (activity as? AppCompatActivity)?.apply {
            setSupportActionBar(binding.toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        binding.toolbar.apply {
            setNavigationOnClickListener {
                viewModel.onClickBack()
            }
        }
    }

    private fun setProfilePhoto(imgUrl: String) {
        binding.avatar.apply {
            Glide.with(context).load(imgUrl).into(this)
        }
    }

}