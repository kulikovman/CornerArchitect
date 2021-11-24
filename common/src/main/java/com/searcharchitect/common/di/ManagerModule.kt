package com.searcharchitect.common.di

import com.searcharchitect.common.manager.ContactManager
import com.searcharchitect.common.manager.IContactManager
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
