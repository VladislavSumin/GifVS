package ru.vladislavsumin.gifvs.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import ru.vladislavsumin.gifvs.domain.GifManager
import ru.vladislavsumin.gifvs.domain.impl.GifManagerImpl
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class MainModuleBindings {
    @Singleton
    @Binds
    abstract fun bindGifManager(gifManagerImpl: GifManagerImpl): GifManager
}