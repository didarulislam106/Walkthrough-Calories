package com.example.walkthroughcalories

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalorieCalculatorApp()
        }
    }
}

@Composable
fun CalorieCalculatorApp() {
    var weight by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("Male") }
    var activityLevel by remember { mutableStateOf("Sedentary") }
    var calories by remember { mutableDoubleStateOf(0.0) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Calorie Consumption Calculator", fontSize = 20.sp)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = weight,
            onValueChange = { weight = it },
            label = { Text("Weight (kg)") }
        )
        Spacer(modifier = Modifier.height(16.dp))

        GenderSelection(selectedGender = gender, onGenderSelected = { gender = it })
        Spacer(modifier = Modifier.height(16.dp))

        IntensityList(selectedLevel = activityLevel, onLevelSelected = { activityLevel = it })
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            calories = calculateCalories(weight.toDoubleOrNull() ?: 0.0, gender, activityLevel)
        }) {
            Text("Calculate")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text("Estimated Calories: $calories kcal", fontSize = 18.sp)
    }
}

@Composable
fun GenderSelection(selectedGender: String, onGenderSelected: (String) -> Unit) {
    Column {
        Text("Select Gender")
        Row {
            listOf("Male", "Female").forEach { gender ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = gender == selectedGender,
                        onClick = { onGenderSelected(gender) }
                    )
                    Text(gender)
                }
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}

@Composable
fun IntensityList(selectedLevel: String, onLevelSelected: (String) -> Unit) {
    val activityLevels = listOf("Light","Usual","Moderate","Hard","Very hard")
    var expanded by remember { mutableStateOf(false) }

    Column {
        Text("Select Intensity Level")
        Box {
            Button(onClick = { expanded = true }) {
                Text(selectedLevel)
            }
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                activityLevels.forEach { level ->
                    DropdownMenuItem(
                        text = { Text(level) },
                        onClick = {
                            onLevelSelected(level)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}


fun calculateCalories(weight: Double, gender: String, activityLevel: String): Double {
    val baseCalories = if (gender == "Male") weight * 24 else weight * 22
    val activityMultiplier = when (activityLevel) {
        "Light" -> 1.3f
        "Usual" -> 1.5f
        "Moderate" -> 1.7f
        "Hard" -> 2f
        "Very hard" -> 2.2f
        else -> 0.0f
    }
    return baseCalories * activityMultiplier
}
