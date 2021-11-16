package com.corner.searcharchitect.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.corner.searcharchitect.base.BaseViewModel
import com.corner.searcharchitect.manager.IContactManager
import com.corner.searcharchitect.navigation.INavigator
import com.corner.searcharchitect.utility.extension.combine
import com.corner.searcharchitect.utility.log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val navigator: INavigator,
    private val contactManager: IContactManager
) : BaseViewModel() {

    var hideKeyboard: (() -> Unit)? = null

    val username = MutableLiveData("")

    val password = MutableLiveData("")

    val isUsernameClearButtonVisibility = username.map { it.isNotEmpty() }

    val isPasswordClearButtonVisibility = password.map { it.isNotEmpty() }

    val isErrorVisibility = MutableLiveData(false)

    val isSignInButtonEnabled = combine(username, password) { username, password ->
        username?.isNotEmpty() == true && password?.isNotEmpty() == true
    }


    fun onClickClearUsername() {
        username.value = ""
    }

    fun onClickClearPassword() {
        password.value = ""
    }

    fun onClickSignIn() {
        viewModelScope.launch {
            if (contactManager.isCorrectCredentials(username.value!!, password.value!!)) {
                hideKeyboard?.invoke()
                contactManager.saveCredentials(username.value!!, password.value!!)
                navigator.actionLoginToSearch()
            } else {
                isErrorVisibility.value = true
            }
        }
    }

}