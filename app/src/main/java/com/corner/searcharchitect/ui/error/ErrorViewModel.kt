package com.corner.searcharchitect.ui.error

import com.corner.searcharchitect.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ErrorViewModel @Inject constructor(
) : BaseViewModel() {

    var restartApp: (() -> Unit)? = null


    fun onClickTryAgain() {
        restartApp?.invoke()
    }

}