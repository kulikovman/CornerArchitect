package com.example.cornerarchitect.di

import com.example.cornerarchitect.helper.ITextHelper
import com.example.cornerarchitect.helper.TextHelper
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

}
