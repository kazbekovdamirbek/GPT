package com.foodlens.presentation.home

import com.google.common.truth.Truth.assertThat
import com.foodlens.domain.model.UserGoal
import com.foodlens.domain.model.UserProfile
import com.foodlens.domain.repository.UserProfileRepository
import com.foodlens.domain.usecase.GetDailyTargetUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlinx.coroutines.Dispatchers

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    private val dispatcher = StandardTestDispatcher()
    private val testScope = TestScope(dispatcher)

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `emits target when profile exists`() = testScope.runTest {
        val repo = FakeUserProfileRepository()
        val viewModel = HomeViewModel(repo, GetDailyTargetUseCase())

        repo.emit(
            UserProfile(
                goal = UserGoal.LOSE_WEIGHT,
                sex = "female",
                age = 28,
                heightCm = 168,
                weightKg = 65,
                activityMultiplier = 1.4
            )
        )
        advanceUntilIdle()

        assertThat(viewModel.state.target).isNotNull()
    }

    private class FakeUserProfileRepository : UserProfileRepository {
        private val flow = MutableStateFlow<UserProfile?>(null)

        override suspend fun saveProfile(profile: UserProfile) {
            flow.value = profile
        }

        override fun observeProfile(): Flow<UserProfile?> = flow

        fun emit(profile: UserProfile) {
            flow.value = profile
        }
    }
}
