package com.example.searcharchitect.ui.detail

import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.searcharchitect.base.BaseViewModel
import com.example.searcharchitect.manager.IContactManager
import com.example.searcharchitect.navigation.INavigator
import com.example.searcharchitect.repositiry.INetworkRepository
import com.example.searcharchitect.utility.log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val navigator: INavigator,
    private val contactManager: IContactManager,
    private val network: INetworkRepository
) : BaseViewModel() {

    //val facebookToken = contactManager.facebookToken

    var setProfilePhoto: ((String) -> Unit)? = null

    var callPhoneNumber: ((String) -> Unit)? = null

    var sendEmail: ((String) -> Unit)? = null

    var openTelegram: ((String) -> Unit)? = null

    var openInstagram: ((String) -> Unit)? = null

    var openFacebook: ((String) -> Unit)? = null

    var openVk: ((String) -> Unit)? = null

    var openLink: ((String) -> Unit)? = null


    val contact = contactManager.getSelectedContact()

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


    init {
        viewModelScope.launch {
            //log("Before try get facebook profile info...")
            //delay(100)

            /*viewModelScope.launch {
                delay(100)
                network.getVkProfileInfo("valera_andreevna").either { link ->
                    setProfilePhoto?.invoke(link)
                }
            }*/

            /*contactManager.apply {
                facebook.value?.let { userId ->
                    log("Facebook user id: $userId")

                    val params = Bundle()
                    params.putString("fields", "full_picture,message")

                    GraphRequest(
                        accessToken = AccessToken.getCurrentAccessToken(),
                        graphPath = "$userId/feed",
                        parameters = params,
                        httpMethod = HttpMethod.GET,
                        callback = { response ->
                            log("Response: $response")
                        }
                    ).executeAsync()

                    facebookToken?.let { token ->
                        log("Facebook token: $token")
                        network.getFacebookProfileInfo(userId, token)
                    }
                }
            }*/
        }
    }


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