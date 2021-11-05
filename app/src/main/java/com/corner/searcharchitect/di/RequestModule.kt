package com.corner.searcharchitect.di

import com.corner.searcharchitect.retrofit.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class RequestModule {

    @Binds
    @ActivityRetainedScoped
    abstract fun bindGoogleSheetsRequest(googleSheetsRequest: GoogleSheetsRequest): IGoogleSheetsRequest

    @Binds
    @ActivityRetainedScoped
    abstract fun bindFacebookRequest(facebookRequest: FacebookRequest): IFacebookRequest

    @Binds
    @ActivityRetainedScoped
    abstract fun bindVkRequest(vkRequest: VkRequest): IVkRequest

}