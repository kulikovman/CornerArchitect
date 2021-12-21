package com.searcharchitect.two.screen.detail

import com.searcharchitect.common.model.Contact

sealed class DetailState {

    data class Default(
        val contact: Contact? = null
    ) : DetailState()

}
