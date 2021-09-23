package com.example.cornerarchitect.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cornerarchitect.utility.Logg
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(

) : ViewModel() {

    val isWhiteLoading = MutableLiveData(false)

    val isSplashScreen = MutableLiveData(false)

    val isErrorVisibility = MutableLiveData(false)

    val errorMessage = MutableLiveData("")


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