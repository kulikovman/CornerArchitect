package com.example.cornerarchitect.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cornerarchitect.manager.IContactManager
import com.example.cornerarchitect.repositiry.IDatabaseRepository
import com.example.cornerarchitect.repositiry.IDatastoreRepository
import com.example.cornerarchitect.repositiry.INetworkRepository
import com.example.cornerarchitect.retrofit.Failure
import com.example.cornerarchitect.utility.getCurrentTime
import com.example.cornerarchitect.utility.log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val network: INetworkRepository,
    private val database: IDatabaseRepository,
    private val datastore: IDatastoreRepository,
    private val contactManager: IContactManager
) : ViewModel() {

    val isSplashScreen = MutableLiveData(false)

    var startSplashTime = 0L

    var animateSplashFadeOut: ((Long, () -> Unit) -> Unit)? = null


    val isUpdateChecking = MutableLiveData(false)

    val isDataLoading = MutableLiveData(false)

    val isPreparation = MutableLiveData(false)


    init {
        showSplash()
        loadContacts()
    }


    private fun loadContacts() {
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
                            contactManager.contacts.value = contacts

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
            contactManager.contacts.value = database.getContacts()
            log("Contacts in database: ${database.getNumberOfContacts()}")

            hidePreparation()
            hideSplash()
        }
    }


    private fun showSplash() {
        startSplashTime = getCurrentTime()
        isSplashScreen.value = true
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

    private fun hideSplash(afterFunc: (() -> Unit)? = null) {
        val timePassed = getCurrentTime() - startSplashTime
        val remainingTime = (MIN_SPLASH_TIME_DURATION - timePassed).takeIf { it > 0 } ?: 0

        animateSplashFadeOut?.invoke(remainingTime) {
            isSplashScreen.value = false
            afterFunc?.invoke()
        }
    }


    companion object {

        const val MIN_SPLASH_TIME_DURATION = 900L

    }

}