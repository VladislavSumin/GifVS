package ru.vladislavsumin.gifvs.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import ru.vladislavsumin.gifvs.dao.AppDatabase
import ru.vladislavsumin.gifvs.dao.GifDao
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.createInstance(context)
    }

    @Singleton
    @Provides
    fun provideGifDao(appDatabase: AppDatabase): GifDao {
        return appDatabase.getGifDao()
    }
}