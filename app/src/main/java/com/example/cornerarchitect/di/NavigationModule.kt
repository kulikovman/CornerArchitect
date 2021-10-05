package com.example.cornerarchitect.di

import com.example.cornerarchitect.navigation.INavigator
import com.example.cornerarchitect.navigation.Navigator
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