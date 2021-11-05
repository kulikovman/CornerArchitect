package com.corner.searcharchitect.di

import com.corner.searcharchitect.manager.ContactManager
import com.corner.searcharchitect.manager.IContactManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ManagerModule {

    @Binds
    @ActivityRetainedScoped
    abstract fun bindContactManager(contactManager: ContactManager): IContactManager

}
