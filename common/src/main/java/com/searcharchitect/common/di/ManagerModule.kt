package com.searcharchitect.common.di

import com.searcharchitect.common.manager.ArchitectsManager
import com.searcharchitect.common.manager.IArchitectsManager
import com.searcharchitect.common.manager.ISettingsManager
import com.searcharchitect.common.manager.SettingsManager
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
    abstract fun bindArchitectsManager(contactManager: ArchitectsManager): IArchitectsManager

    @Binds
    @ActivityRetainedScoped
    abstract fun bindSettingsManager(settingsManager: SettingsManager): ISettingsManager

}
