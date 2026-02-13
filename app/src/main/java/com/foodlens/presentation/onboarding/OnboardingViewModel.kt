package com.foodlens.presentation.onboarding

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodlens.domain.model.UserProfile
import com.foodlens.domain.usecase.SaveOnboardingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val saveOnboardingUseCase: SaveOnboardingUseCase
) : ViewModel() {

    var state by mutableStateOf(OnboardingUiState())
        private set

    private var onComplete: (() -> Unit)? = null

    fun onAction(action: OnboardingAction) {
        when (action) {
            is OnboardingAction.GoalChanged -> state = state.copy(goal = action.goal)
            is OnboardingAction.SexChanged -> state = state.copy(sex = action.sex)
            is OnboardingAction.AgeChanged -> state = state.copy(age = action.age)
            is OnboardingAction.HeightChanged -> state = state.copy(heightCm = action.heightCm)
            is OnboardingAction.WeightChanged -> state = state.copy(weightKg = action.weightKg)
            is OnboardingAction.ActivityChanged -> state = state.copy(activityMultiplier = action.multiplier)
            OnboardingAction.Save -> saveProfile()
        }
    }

    fun setOnComplete(callback: () -> Unit) {
        onComplete = callback
    }

    private fun saveProfile() {
        viewModelScope.launch {
            state = state.copy(isSaving = true, error = null)
            runCatching {
                val profile = UserProfile(
                    goal = state.goal,
                    sex = state.sex,
                    age = state.age.toInt(),
                    heightCm = state.heightCm.toInt(),
                    weightKg = state.weightKg.toInt(),
                    activityMultiplier = state.activityMultiplier.toDouble()
                )
                saveOnboardingUseCase(profile)
            }.onSuccess {
                onComplete?.invoke()
            }.onFailure {
                state = state.copy(error = it.message ?: "Unknown error")
            }
            state = state.copy(isSaving = false)
        }
    }
}
