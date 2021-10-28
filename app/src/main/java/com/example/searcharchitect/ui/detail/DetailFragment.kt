package com.example.searcharchitect.ui.detail

import android.content.ActivityNotFoundException
import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.searcharchitect.R
import com.example.searcharchitect.base.BaseFragment
import com.example.searcharchitect.databinding.DetailFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<DetailFragmentBinding, DetailViewModel>() {

    override fun getLayoutId(): Int = R.layout.detail_fragment

    override val viewModel: DetailViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()

        viewModel.setProfilePhoto = ::setProfilePhoto
        viewModel.callPhoneNumber = ::callPhoneNumber
        viewModel.sendEmail = ::sendEmail
        viewModel.openTelegram = ::openTelegram
        viewModel.openInstagram = ::openInstagram
        viewModel.openFacebook = ::openFacebook
        viewModel.openVk = ::openVk
        viewModel.openLink = ::openLink
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

    private fun loadAvatar() {
        /*VK.execute(FriendsService().friendsGet(
            userId = "valera_andreevna"
        ), object: VKApiCallback<FriendsGetFieldsResponse> {
            override fun success(result: FriendsGetFieldsResponse) {
                // you stuff is here
            }
            override fun fail(error: Exception) {

            }
        })*/

        /*VK.execute(UsersGetNameCase(), object: VKApiCallback<List<UsersUserXtrCounters>> {
            override fun success(result: List<UsersUserXtrCounters>) {
            }
            override fun fail(error: VKApiExecutionException) {
            }
        })*/

    /*viewModel.facebookToken?.let { token ->
            log("Facebook token: $token")
            viewModel.facebook.observe(viewLifecycleOwner) { id ->
                val imgUrl = "https://graph.facebook.com/$id/picture?type=large&access_token=$token"
                log("Link: $imgUrl")

                binding.avatar.apply {
                    Glide.with(context).load(imgUrl).into(this);
                }
            }
        }*/
    }

    private fun setProfilePhoto(imgUrl: String) {
        binding.avatar.apply {
            Glide.with(context).load(imgUrl).into(this)
        }
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

    private fun openTelegram(profileId: String) {
        val appUri = Uri.parse("https://t.me/$profileId")
        val appIntent = Intent(Intent.ACTION_VIEW, appUri).apply {
            `package` = "org.telegram.messenger"
        }

        val browserUri = Uri.parse("https://t.me/$profileId")
        val browserIntent = Intent(Intent.ACTION_VIEW, browserUri)

        try {
            startActivity(appIntent)
        } catch (e: ActivityNotFoundException) {
            startActivity(browserIntent)
        }
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

    private fun openLink(link: String) {
        val correctLink = if (!link.startsWith("http://")
            || !link.startsWith("https://")
        ) "http://$link" else link

        val uri = Uri.parse(correctLink)
        val intent = Intent(Intent.ACTION_VIEW, uri)

        startActivity(intent)
    }

}