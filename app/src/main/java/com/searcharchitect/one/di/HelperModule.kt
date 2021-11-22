package com.searcharchitect.one.di

import com.searcharchitect.one.utility.helper.ITextHelper
import com.searcharchitect.one.utility.helper.IToastHelper
import com.searcharchitect.one.utility.helper.TextHelper
import com.searcharchitect.one.utility.helper.ToastHelper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class HelperModule {

    @Binds
    @Singleton
    abstract fun bindTextHelper(textHelper: TextHelper): ITextHelper

    @Binds
    @Singleton
    abstract fun bindToastHelper(toastHelper: ToastHelper): IToastHelper

}
