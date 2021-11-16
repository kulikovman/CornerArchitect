package com.corner.searcharchitect.ui.info

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.corner.searcharchitect.base.BaseViewModel
import com.corner.searcharchitect.manager.IContactManager
import com.corner.searcharchitect.utility.extension.combine
import com.corner.searcharchitect.utility.helper.ITextHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InfoViewModel @Inject constructor(
    private val contactManager: IContactManager,
    private val text: ITextHelper
) : BaseViewModel() {

    var dismissDialog: (() -> Unit)? = null

    var sendEmail: ((String) -> Unit)? = null


    val appVersion = MutableLiveData("")

    private val dataVersion = MutableLiveData("")

    val version = combine(appVersion, dataVersion) { appVersion, dataVersion ->
        "$appVersion / $dataVersion"
    }


    init {
        viewModelScope.launch {
            dataVersion.value = contactManager.getDataVersion()
        }
    }


    fun onClickEmail() {
        dismissDialog?.invoke()
        sendEmail?.invoke(text.psthvEmail())
    }

}