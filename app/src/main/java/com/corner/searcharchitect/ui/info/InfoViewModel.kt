package com.corner.searcharchitect.ui.info

import androidx.lifecycle.MutableLiveData
import com.corner.searcharchitect.base.BaseViewModel
import com.corner.searcharchitect.utility.helper.ITextHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InfoViewModel @Inject constructor(
    private val text: ITextHelper
) : BaseViewModel() {

    var dismissDialog: (() -> Unit)? = null

    var sendEmail: ((String) -> Unit)? = null


    val appVersion = MutableLiveData("")


    fun onClickEmail() {
        dismissDialog?.invoke()
        sendEmail?.invoke(text.psthvEmail())
    }

}