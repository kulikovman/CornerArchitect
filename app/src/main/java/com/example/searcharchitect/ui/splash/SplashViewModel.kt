package com.example.searcharchitect.ui.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.searcharchitect.base.BaseViewModel
import com.example.searcharchitect.manager.IContactManager
import com.example.searcharchitect.navigation.INavigator
import com.example.searcharchitect.repositiry.INetworkRepository
import com.example.searcharchitect.retrofit.Failure
import com.example.searcharchitect.utility.log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val navigator: INavigator,
    private val contactManager: IContactManager,
    private val network: INetworkRepository
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
        isUpdateChecking.value = false
        getContactsFromDatabase()
    }

    private fun handleLoadDataFailure(failure: Failure) {
        log("Failure data loading: $failure")
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