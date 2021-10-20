package com.example.searcharchitect.di

import com.example.searcharchitect.utility.helper.ITextHelper
import com.example.searcharchitect.utility.helper.IToastHelper
import com.example.searcharchitect.utility.helper.TextHelper
import com.example.searcharchitect.utility.helper.ToastHelper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ActivityRetainedScoped
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
