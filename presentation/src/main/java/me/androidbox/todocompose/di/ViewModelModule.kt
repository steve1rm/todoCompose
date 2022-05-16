package me.androidbox.todocompose.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.multibindings.IntoMap
import me.androidbox.todocompose.viewmodel.ShareViewModel

@Module
@InstallIn(ViewModelComponent::class)
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ShareViewModel::class)
    fun bindsShareViewModel(shareViewModel: ShareViewModel): ViewModel
}