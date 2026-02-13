package com.foodlens.domain.repository

import com.foodlens.domain.model.UserProfile
import kotlinx.coroutines.flow.Flow

interface UserProfileRepository {
    suspend fun saveProfile(profile: UserProfile)
    fun observeProfile(): Flow<UserProfile?>
}
