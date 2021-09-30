package com.example.cornerarchitect.di

import com.example.cornerarchitect.retrofit.GoogleSheetsRequest
import com.example.cornerarchitect.retrofit.IGoogleSheetsRequest
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

}