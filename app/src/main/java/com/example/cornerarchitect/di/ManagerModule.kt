package com.example.cornerarchitect.di

import com.example.cornerarchitect.manager.ContactManager
import com.example.cornerarchitect.manager.IContactManager
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
