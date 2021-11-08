package com.corner.searcharchitect.ui.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.corner.searcharchitect.base.BaseViewModel
import com.corner.searcharchitect.manager.IContactManager
import com.corner.searcharchitect.navigation.INavigator
import com.corner.searcharchitect.repositiry.INetworkRepository
import com.corner.searcharchitect.retrofit.Failure
import com.corner.searcharchitect.utility.helper.ITextHelper
import com.corner.searcharchitect.utility.helper.IToastHelper
import com.corner.searcharchitect.utility.log
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
                            viewModelScope.launch {

                                // todo Обновлять ссылки на фото при каждом запуске
                                // Если окажется, что ссылки временные...

                                val domains = contacts.mapNotNull { it.vk }
                                network.getVkProfileInfoList(domains).either { profileInfoList ->
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

                                contactManager.updateAppData(version, contacts)
                                isDataLoading.value = false

                                navigator.actionSplashToSearch()
                            }
                        }
                    } else getContactsFromDatabase()
                }
            }
        }
    }

    private fun handleCheckUpdateFailure(failure: Failure) {
        log("Failure update checking: $failure")

        when (failure) {
            is Failure.ConnectionError -> toast.showLong(text.connectionError())
            else -> toast.showLong(text.unknownError())
        }

        isUpdateChecking.value = false
        getContactsFromDatabase()
    }

    private fun handleLoadDataFailure(failure: Failure) {
        log("Failure data loading: $failure")

        when (failure) {
            is Failure.ConnectionError -> toast.showLong(text.connectionError())
            else -> toast.showLong(text.unknownError())
        }

        isDataLoading.value = false
        getContactsFromDatabase()
    }

    private fun getContactsFromDatabase() {
        viewModelScope.launch {
            isPreparation.value = true
            contactManager.loadContactsFromDatabase()
            isPreparation.value = false

            navigator.actionSplashToSearch()
        }
    }

}