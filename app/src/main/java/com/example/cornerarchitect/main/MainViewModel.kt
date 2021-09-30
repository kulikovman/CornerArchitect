package com.example.cornerarchitect.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cornerarchitect.manager.ContactManager
import com.example.cornerarchitect.manager.IContactManager
import com.example.cornerarchitect.repositiry.DatabaseRepository
import com.example.cornerarchitect.repositiry.IDatabaseRepository
import com.example.cornerarchitect.repositiry.INetworkRepository
import com.example.cornerarchitect.repositiry.NetworkRepository
import com.example.cornerarchitect.retrofit.Failure
import com.example.cornerarchitect.utility.Logg
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
            Logg.d { "Before request" }
            /*network.getDataVersion().either(::handleFailure) { dataVersion ->
                Logg.d { "dataVersion = $dataVersion" }
            }*/

            network.getContactList().either { result ->
                Logg.d { "Contacts list size: ${result.size}" }
                contactManager.contacts.value = result
            }

            Logg.d { "After request" }
        }
    }

    private fun handleFailure(failure: Failure) {
        Logg.d { "$failure" }
    }


    fun actionAfterPauseApp() {
        Logg.d { "Application paused" }

    }

    fun actionAfterResumeApp() {
        Logg.d { "Application resumed" }

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