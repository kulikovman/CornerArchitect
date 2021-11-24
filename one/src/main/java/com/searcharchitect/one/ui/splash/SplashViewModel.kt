package com.searcharchitect.one.ui.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.searcharchitect.common.manager.IContactManager
import com.searcharchitect.common.model.Contact
import com.searcharchitect.common.repositiry.INetworkRepository
import com.searcharchitect.common.retrofit.Failure
import com.searcharchitect.common.helper.ITextHelper
import com.searcharchitect.common.helper.IToastHelper
import com.searcharchitect.common.utility.log
import com.searcharchitect.one.base.BaseViewModel
import com.searcharchitect.one.navigation.INavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val navigator: INavigator,
    private val contactManager: IContactManager,
    private val network: INetworkRepository,
    private val toast: IToastHelper,
    private val text: ITextHelper
) : BaseViewModel() {

    val isUpdateChecking = MutableLiveData(false)

    val isDataLoading = MutableLiveData(false)

    val isPreparation = MutableLiveData(false)


    init {
        getContacts()
    }


    private fun getContacts() {
        viewModelScope.launch {
            isUpdateChecking.value = true

            network.getDataVersion().either(::handleCheckUpdateFailure) { version ->
                log("Data version from sheets: $version")
                viewModelScope.launch {
                    val isExistNewVersion = contactManager.isNewVersion(version)
                    isUpdateChecking.value = false

                    if (isExistNewVersion) {
                        isDataLoading.value = true
                        network.getContactList().either(::handleLoadDataFailure) { contacts ->
                            log("Contacts in google sheets: ${contacts.size}")
                            isDataLoading.value = false

                            viewModelScope.launch {
                                isPreparation.value = true
                                loadAvatarLinks(contacts)
                                contactManager.updateAppData(version, contacts)

                                openNextScreen(contacts)
                                isPreparation.value = true
                            }
                        }
                    } else getContactsFromDatabase()
                }
            }
        }
    }

    private fun getContactsFromDatabase() {
        viewModelScope.launch {
            isPreparation.value = true
            contactManager.loadContactsFromDatabase()

            contactManager.getContacts().value?.let { contacts ->
                if (contacts.isNotEmpty()) {
                    viewModelScope.launch {
                        loadAvatarLinks(contacts)
                        openNextScreen(contacts)
                    }
                } else navigator.actionSplashToError()
            }

            isPreparation.value = false
        }
    }

    private suspend fun openNextScreen(contacts: List<Contact>) {
        contactManager.createPasswordMap(contacts)

        when {
            contactManager.isExistCorrectCredentials() -> navigator.actionSplashToSearch()
            else -> navigator.actionSplashToLogin()
        }
    }

    private suspend fun loadAvatarLinks(contacts: List<Contact>) {
        val domains = contacts.mapNotNull { it.vk }
        network.getVkProfileInfoList(domains).either(::handleAvatarLoadingFailure) { profileInfoList ->
            log("Profile info list size: ${profileInfoList.size}")
            viewModelScope.launch {
                profileInfoList.forEach { profileInfo ->
                    contacts.find { it.vk == profileInfo.domain }?.apply {
                        previewLink = profileInfo.previewLink
                        photoLink = profileInfo.photoLink
                    }
                }
            }
        }

        log("Contacts with preview links: ${contacts.filter { it.previewLink != null }.size}")
    }

    private fun handleCheckUpdateFailure(failure: Failure) {
        log("Failure update checking: $failure")
        FirebaseCrashlytics.getInstance().log("handleCheckUpdateFailure")
        showErrorMessage(failure)

        isUpdateChecking.value = false
        getContactsFromDatabase()
    }

    private fun handleLoadDataFailure(failure: Failure) {
        log("Failure data loading: $failure")
        FirebaseCrashlytics.getInstance().log("handleLoadDataFailure")
        showErrorMessage(failure)

        isDataLoading.value = false
        getContactsFromDatabase()
    }

    private fun handleAvatarLoadingFailure(failure: Failure) {
        log("Failure data loading: $failure")
        FirebaseCrashlytics.getInstance().log("handleLoadDataFailure")
        showErrorMessage(failure)
    }

    private fun showErrorMessage(failure: Failure) {
        when (failure) {
            is Failure.ConnectionError -> toast.showLong(text.connectionError())
            is Failure.UnknownError -> toast.showLong(text.unknownError())
        }
    }

}