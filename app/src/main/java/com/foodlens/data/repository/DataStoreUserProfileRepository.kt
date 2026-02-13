package com.foodlens.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.foodlens.domain.model.UserGoal
import com.foodlens.domain.model.UserProfile
import com.foodlens.domain.repository.UserProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreUserProfileRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : UserProfileRepository {

    override suspend fun saveProfile(profile: UserProfile) {
        dataStore.edit { prefs ->
            prefs[Keys.GOAL] = profile.goal.name
            prefs[Keys.SEX] = profile.sex
            prefs[Keys.AGE] = profile.age
            prefs[Keys.HEIGHT] = profile.heightCm
            prefs[Keys.WEIGHT] = profile.weightKg
            prefs[Keys.ACTIVITY] = profile.activityMultiplier
        }
    }

    override fun observeProfile(): Flow<UserProfile?> {
        return dataStore.data.map { prefs ->
            val goalValue = prefs[Keys.GOAL] ?: return@map null
            UserProfile(
                goal = UserGoal.valueOf(goalValue),
                sex = prefs[Keys.SEX] ?: "female",
                age = prefs[Keys.AGE] ?: 25,
                heightCm = prefs[Keys.HEIGHT] ?: 170,
                weightKg = prefs[Keys.WEIGHT] ?: 70,
                activityMultiplier = prefs[Keys.ACTIVITY] ?: 1.2
            )
        }
    }

    private object Keys {
        val GOAL = stringPreferencesKey("goal")
        val SEX = stringPreferencesKey("sex")
        val AGE = intPreferencesKey("age")
        val HEIGHT = intPreferencesKey("height")
        val WEIGHT = intPreferencesKey("weight")
        val ACTIVITY = doublePreferencesKey("activity")
    }
}
