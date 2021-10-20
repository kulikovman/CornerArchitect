package com.example.searcharchitect.ui.detail

import androidx.lifecycle.map
import com.example.searcharchitect.base.BaseViewModel
import com.example.searcharchitect.manager.IContactManager
import com.example.searcharchitect.navigation.INavigator
import com.example.searcharchitect.utility.log
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val navigator: INavigator,
    private val contactManager: IContactManager
) : BaseViewModel() {

    var callPhoneNumber: ((String) -> Unit)? = null

    var sendEmail: ((String) -> Unit)? = null

    var openInstagram: ((String) -> Unit)? = null

    var openFacebook: ((String) -> Unit)? = null

    var openVk: ((String) -> Unit)? = null

    var openLink: ((String) -> Unit)? = null


    val contact = contactManager.getSelectedContact()

    val name = contact.map {
        "${it.name}\n${it.surname}"
    }

    val city = contact.map { it.city }

    val specialization = contact.map { it.specialization }

    val work = contact.map { it.work }

    val position = contact.map { it.position }

    val phone = contact.map { it.phone }

    val email = contact.map { it.email }

    val instagram = contact.map { it.instagram }

    val facebook = contact.map { it.facebook }

    val vk = contact.map { it.vk }

    val link = contact.map { it.link }

    val note = contact.map { it.note }


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