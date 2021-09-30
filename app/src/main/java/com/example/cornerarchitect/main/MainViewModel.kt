package com.example.cornerarchitect.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cornerarchitect.manager.IContactManager
import com.example.cornerarchitect.repositiry.IDatabaseRepository
import com.example.cornerarchitect.repositiry.INetworkRepository
import com.example.cornerarchitect.retrofit.Failure
import com.example.cornerarchitect.utility.log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val network: INetworkRepository,
    private val database: IDatabaseRepository,
    private val contactManager: IContactManager
) : ViewModel() {

    val isWhiteLoading = MutableLiveData(false)

    val isSplashScreen = MutableLiveData(false)

    val isErrorVisibility = MutableLiveData(false)

    val errorMessage = MutableLiveData("")


    init {
        viewModelScope.launch {
            log("Before request")
            network.getDataVersion().either(::handleFailure) { dataVersion ->
                log("dataVersion = $dataVersion")
            }

            /*network.getContactList().either { result ->
                Logg.d { "Contacts list size: ${result.size}" }
                contactManager.contacts.value = result
            }*/

            log("After request")
        }
    }

    private fun handleFailure(failure: Failure) {
        log("$failure")
    }


    fun actionAfterPauseApp() {
        log("Application paused")

    }

    fun actionAfterResumeApp() {
        log("Application resumed")
    }


    /*val isWhiteLoading = MutableLiveData(false)

    val isTransparentLoading = MutableLiveData(false)


    var animateSplashFadeOut: ((Long, () -> Unit) -> Unit)? = null

    val isSplashScreen = MutableLiveData(false)

    var startSplashTime = 0L


    val isErrorVisibility = MutableLiveData(false)

    val errorMessage = MutableLiveData("")


    init {
        viewModelScope.launch {
            showSplash()

        }
    }





    fun whiteLoading() {
        isWhiteLoading.value = true
    }

    fun transparentLoading() {
        isTransparentLoading.value = true
    }

    fun hideLoading() {
        isTransparentLoading.value = false
        isWhiteLoading.value = false
    }


    private fun showSplash() {
        startSplashTime = getCurrentTime()
        isSplashScreen.value = true
    }

    private fun hideSplash(afterFunc: (() -> Unit)? = null) {
        val timePassed = getCurrentTime() - startSplashTime
        val remainingTime = (MIN_SPLASH_TIME - timePassed).takeIf { it > 0 } ?: 0

        animateSplashFadeOut?.invoke(remainingTime) {
            isSplashScreen.value = false
            afterFunc?.invoke()
        }
    }

    fun showError(message: String, delay: Long) {
        Logg.d { "showError: $message / $delay" }
        errorMessage.value = message
        isErrorVisibility.value = true
        time.startCountDownTimer(delay) {
            hideError()
        }
    }

    fun hideError() {
        isErrorVisibility.value = false
        errorMessage.value = ""
    }*/


    companion object {

        const val MIN_SPLASH_TIME = 900L

    }

}