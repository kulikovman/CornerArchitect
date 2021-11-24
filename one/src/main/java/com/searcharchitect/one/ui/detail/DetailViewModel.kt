package com.searcharchitect.one.ui.detail

import androidx.lifecycle.map
import com.searcharchitect.common.manager.IContactManager
import com.searcharchitect.common.repositiry.INetworkRepository
import com.searcharchitect.one.base.BaseViewModel
import com.searcharchitect.one.navigation.INavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val navigator: INavigator,
    private val contactManager: IContactManager
) : BaseViewModel() {

    var setProfilePhoto: ((String) -> Unit)? = null

    var callPhoneNumber: ((String) -> Unit)? = null

    var sendEmail: ((String) -> Unit)? = null

    var openTelegram: ((String) -> Unit)? = null

    var openInstagram: ((String) -> Unit)? = null

    var openFacebook: ((String) -> Unit)? = null

    var openVk: ((String) -> Unit)? = null

    var openLink: ((String) -> Unit)? = null


    private val contact = contactManager.getSelectedContact()

    val name = contact.map {
        "${it.name}\n${it.surname}"
    }

    val location = contact.map {
        "${it.city} / ${it.region}"
    }

    val specialization = contact.map { it.specialization }

    val work = contact.map { it.work }

    val position = contact.map { it.position }

    val phone = contact.map { it.phone }

    val email = contact.map { it.email }

    val telegram = contact.map { it.telegram }

    val instagram = contact.map { it.instagram }

    val facebook = contact.map { it.facebook }

    val vk = contact.map { it.vk }

    val link = contact.map { it.link }

    val note = contact.map { it.note }

    val photoLink = contact.map { it.photoLink }


    fun onClickBack() {
        navigator.goBack()
    }

    fun onClickPhone() {
        phone.value?.let { phone ->
            callPhoneNumber?.invoke(phone)
        }
    }

    fun onClickEmail() {
        email.value?.let { email ->
            sendEmail?.invoke(email)
        }
    }

    fun onClickTelegram() {
        telegram.value?.let { profileId ->
            openTelegram?.invoke(profileId)
        }
    }

    fun onClickInstagram() {
        instagram.value?.let { profileId ->
            openInstagram?.invoke(profileId)
        }
    }

    fun onClickFacebook() {
        facebook.value?.let { profileId ->
            openFacebook?.invoke(profileId)
        }
    }

    fun onClickVk() {
        vk.value?.let { profileId ->
            openVk?.invoke(profileId)
        }
    }

    fun onClickLink() {
        link.value?.let { link ->
            openLink?.invoke(link)
        }
    }

}