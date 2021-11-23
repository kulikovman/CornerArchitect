package com.searcharchitect.one.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.searcharchitect.one.base.BaseViewModel
import com.searcharchitect.one.manager.IContactManager
import com.searcharchitect.one.navigation.INavigator
import com.searcharchitect.one.utility.extension.combine
import com.searcharchitect.one.utility.helper.ITextHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val navigator: INavigator,
    private val contactManager: IContactManager,
    private val text: ITextHelper
) : BaseViewModel() {

    var hideKeyboard: (() -> Unit)? = null

    var sendEmail: ((String) -> Unit)? = null

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

    fun onClickEmail() {
        sendEmail?.invoke(text.psthvEmail())
    }

}