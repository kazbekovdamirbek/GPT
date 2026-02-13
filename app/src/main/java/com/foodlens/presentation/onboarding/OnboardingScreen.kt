package com.foodlens.presentation.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.foodlens.domain.model.UserGoal
import androidx.compose.material3.ExposedDropdownMenu

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingScreen(
    state: OnboardingUiState,
    onAction: (OnboardingAction) -> Unit
) {
    val expanded = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(text = "Настройка профиля", style = MaterialTheme.typography.headlineSmall)

        ExposedDropdownMenuBox(
            expanded = expanded.value,
            onExpandedChange = { expanded.value = !expanded.value }
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                value = state.goal.name,
                onValueChange = {},
                label = { Text("Цель") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded.value) },
                readOnly = true
            )
            ExposedDropdownMenu(expanded = expanded.value, onDismissRequest = { expanded.value = false }) {
                UserGoal.entries.forEach { goal ->
                    DropdownMenuItem(
                        text = { Text(goal.name) },
                        onClick = {
                            onAction(OnboardingAction.GoalChanged(goal))
                            expanded.value = false
                        }
                    )
                }
            }
        }

        OutlinedTextField(
            value = state.sex,
            onValueChange = { onAction(OnboardingAction.SexChanged(it)) },
            label = { Text("Пол (male/female)") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = state.age,
            onValueChange = { onAction(OnboardingAction.AgeChanged(it)) },
            label = { Text("Возраст") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = state.heightCm,
            onValueChange = { onAction(OnboardingAction.HeightChanged(it)) },
            label = { Text("Рост (см)") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = state.weightKg,
            onValueChange = { onAction(OnboardingAction.WeightChanged(it)) },
            label = { Text("Вес (кг)") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = state.activityMultiplier,
            onValueChange = { onAction(OnboardingAction.ActivityChanged(it)) },
            label = { Text("Активность (1.2-1.9)") },
            modifier = Modifier.fillMaxWidth()
        )

        state.error?.let {
            Text(text = it, color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(4.dp))
        Button(
            onClick = { onAction(OnboardingAction.Save) },
            modifier = Modifier.fillMaxWidth(),
            enabled = !state.isSaving
        ) {
            Text(if (state.isSaving) "Сохранение..." else "Продолжить")
        }
    }
}
