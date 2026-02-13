package com.foodlens.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.room.Room
import com.foodlens.data.local.FoodLensDatabase
import com.foodlens.data.repository.DataStoreUserProfileRepository
import com.foodlens.domain.repository.UserProfileRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindUserProfileRepository(
        impl: DataStoreUserProfileRepository
    ): UserProfileRepository
}

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = { context.preferencesDataStoreFile("user_profile.preferences_pb") }
        )
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): FoodLensDatabase {
        return Room.databaseBuilder(
            context,
            FoodLensDatabase::class.java,
            "foodlens.db"
        ).fallbackToDestructiveMigration().build()
    }
}
