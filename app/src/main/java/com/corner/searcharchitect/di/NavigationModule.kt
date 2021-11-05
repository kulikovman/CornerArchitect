package com.corner.searcharchitect.di

import com.corner.searcharchitect.navigation.INavigator
import com.corner.searcharchitect.navigation.Navigator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class NavigationModule {

    @Binds
    @ActivityRetainedScoped
    abstract fun bindNavigator(navigator: Navigator): INavigator

}