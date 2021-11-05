package com.example.searcharchitect.ui.info

import com.example.searcharchitect.base.BaseViewModel
import com.example.searcharchitect.utility.helper.ITextHelper
import com.example.searcharchitect.utility.log
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InfoViewModel @Inject constructor(
    private val text: ITextHelper
) : BaseViewModel() {

    var dismissDialog: (() -> Unit)? = null

    var sendEmail: ((String) -> Unit)? = null


    fun onClickEmail() {
        dismissDialog?.invoke()
        sendEmail?.invoke(text.psthvEmail())
    }

}