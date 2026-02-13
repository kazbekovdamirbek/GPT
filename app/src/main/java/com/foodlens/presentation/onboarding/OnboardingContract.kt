package com.foodlens.presentation.onboarding

import com.foodlens.domain.model.UserGoal

data class OnboardingUiState(
    val goal: UserGoal = UserGoal.MAINTAIN,
    val sex: String = "female",
    val age: String = "25",
    val heightCm: String = "170",
    val weightKg: String = "70",
    val activityMultiplier: String = "1.2",
    val isSaving: Boolean = false,
    val error: String? = null
)

sealed interface OnboardingAction {
    data class GoalChanged(val goal: UserGoal) : OnboardingAction
    data class SexChanged(val sex: String) : OnboardingAction
    data class AgeChanged(val age: String) : OnboardingAction
    data class HeightChanged(val heightCm: String) : OnboardingAction
    data class WeightChanged(val weightKg: String) : OnboardingAction
    data class ActivityChanged(val multiplier: String) : OnboardingAction
    data object Save : OnboardingAction
}
