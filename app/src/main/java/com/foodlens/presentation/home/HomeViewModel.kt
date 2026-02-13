package com.foodlens.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodlens.domain.repository.UserProfileRepository
import com.foodlens.domain.usecase.GetDailyTargetUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userProfileRepository: UserProfileRepository,
    private val getDailyTargetUseCase: GetDailyTargetUseCase
) : ViewModel() {

    var state by mutableStateOf(HomeUiState())
        private set

    init {
        viewModelScope.launch {
            userProfileRepository.observeProfile().collectLatest { profile ->
                state = if (profile == null) {
                    HomeUiState()
                } else {
                    HomeUiState(target = getDailyTargetUseCase(profile))
                }
            }
        }
    }
}
