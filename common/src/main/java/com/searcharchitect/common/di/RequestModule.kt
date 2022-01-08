package com.searcharchitect.common.di

import com.searcharchitect.common.retrofit.GoogleSheetsRequest
import com.searcharchitect.common.retrofit.IGoogleSheetsRequest
import com.searcharchitect.common.retrofit.IVkRequest
import com.searcharchitect.common.retrofit.VkRequest
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
    abstract fun bindVkRequest(vkRequest: VkRequest): IVkRequest

}