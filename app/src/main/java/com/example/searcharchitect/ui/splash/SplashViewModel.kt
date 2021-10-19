package com.example.searcharchitect.ui.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.searcharchitect.base.BaseViewModel
import com.example.searcharchitect.manager.IContactManager
import com.example.searcharchitect.navigation.INavigator
import com.example.searcharchitect.repositiry.IDatabaseRepository
import com.example.searcharchitect.repositiry.IDatastoreRepository
import com.example.searcharchitect.repositiry.INetworkRepository
import com.example.searcharchitect.retrofit.Failure
import com.example.searcharchitect.utility.log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val navigator: INavigator,
    private val network: INetworkRepository,
    private val database: IDatabaseRepository,
    private val datastore: IDatastoreRepository,
    private val contact: IContactManager,
) : BaseViewModel() {

    val isUpdateChecking = MutableLiveData(false)

    val isDataLoading = MutableLiveData(false)

    val isPreparation = MutableLiveData(false)


    init {
        getContacts()
    }


    private fun getContacts() {
        viewModelScope.launch {
            showUpdateChecking()
            network.getDataVersion().either(::handleCheckUpdateFailure) { dataVersion ->
                log("Data version from sheets: $dataVersion")
                viewModelScope.launch {
                    val appDataVersion = datastore.getAppDataVersion()
                    hideUpdateChecking()

                    if (dataVersion > appDataVersion) {
                        showDataLoading()
                        network.getContactList().either(::handleLoadDataFailure) { contacts ->
                            log("Contacts in google sheets: ${contacts.size}")
                            contact.contacts.value = contacts

                            viewModelScope.launch {
                                database.removeAllContacts()
                                database.addContacts(contacts)
                                datastore.updateAppDataVersion(dataVersion)
                            }

                            hideDataLoading()
                            hideSplash()
                        }
                    } else getContactsFromDatabase()
                }
            }
        }
    }

    private fun handleCheckUpdateFailure(failure: Failure) {
        log("Failure update checking: $failure")
        hideUpdateChecking()
        getContactsFromDatabase()
    }

    private fun handleLoadDataFailure(failure: Failure) {
        log("Failure data loading: $failure")
        hideDataLoading()
        getContactsFromDatabase()
    }

    private fun getContactsFromDatabase() {
        viewModelScope.launch {
            showPreparation()

            database.getContacts().let { contacts ->
                log("Contacts in database: ${contacts.size}")
                contact.contacts.value = contacts
            }

            hidePreparation()
            hideSplash()
        }
    }

    private fun showUpdateChecking() {
        isUpdateChecking.value = true
    }

    private fun showDataLoading() {
        isDataLoading.value = true
    }

    private fun showPreparation() {
        isPreparation.value = true
    }

    private fun hideUpdateChecking() {
        isUpdateChecking.value = false
    }

    private fun hideDataLoading() {
        isDataLoading.value = false
    }

    private fun hidePreparation() {
        isPreparation.value = false
    }

    private fun hideSplash() {
        navigator.actionSplashToSearch()
    }

}