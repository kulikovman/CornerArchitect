package com.searcharchitect.one.ui.info

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.searcharchitect.common.utility.extension.combine
import com.searcharchitect.common.helper.ITextHelper
import com.searcharchitect.common.manager.ISettingsManager
import com.searcharchitect.one.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InfoViewModel @Inject constructor(
    private val settings: ISettingsManager,
    private val text: ITextHelper
) : BaseViewModel() {

    var dismissDialog: (() -> Unit)? = null

    var sendEmail: ((String) -> Unit)? = null


    val appVersion = MutableLiveData("")

    private val dataVersion = MutableLiveData("")

    val version = combine(
        appVersion,
        dataVersion
    ) { appVersion, dataVersion ->
        "$appVersion / $dataVersion"
    }


    init {
        viewModelScope.launch {
            dataVersion.value = settings.getDataVersion().toString()
        }
    }


    fun onClickEmail() {
        dismissDialog?.invoke()
        sendEmail?.invoke(text.psthvEmail())
    }

}