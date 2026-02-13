package com.foodlens.domain.usecase

import com.foodlens.domain.model.UserProfile
import com.foodlens.domain.repository.UserProfileRepository
import javax.inject.Inject

class SaveOnboardingUseCase @Inject constructor(
    private val userProfileRepository: UserProfileRepository
) {
    suspend operator fun invoke(profile: UserProfile) {
        require(profile.age in 12..99) { "Age must be between 12 and 99" }
        require(profile.heightCm in 120..240) { "Height must be between 120 and 240 cm" }
        require(profile.weightKg in 30..250) { "Weight must be between 30 and 250 kg" }
        userProfileRepository.saveProfile(profile)
    }
}
