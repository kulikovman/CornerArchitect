package com.example.cornerarchitect.ui.detail

import androidx.lifecycle.map
import com.example.cornerarchitect.base.BaseViewModel
import com.example.cornerarchitect.manager.IContactManager
import com.example.cornerarchitect.utility.log
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val contactManager: IContactManager
) : BaseViewModel() {

    var callPhoneNumber: ((String) -> Unit)? = null

    var sendEmail: ((String) -> Unit)? = null

    var openInstagram: ((String) -> Unit)? = null

    var openFacebook: ((String) -> Unit)? = null

    var openVk: ((String) -> Unit)? = null


    val contact = contactManager.selectedContact

    val name = contact.map { it.name }

    val surname = contact.map { it.surname }

    val specialization = contact.map { contact ->
        var text = ""
        contact.specialization.forEach { item ->
            text += if (text.isEmpty()) item else " / $item"
        }
        text
    }

    val work = contact.map { it.work }

    val position = contact.map { it.position }

    val phone = contact.map { it.phone }

    val email = contact.map { it.email }

    val instagram = contact.map { it.instagram }

    val facebook = contact.map { it.facebook }

    val vk = contact.map { it.vk }


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

}