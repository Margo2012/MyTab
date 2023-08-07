package com.example.mytab.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.mytab.data.DataUIState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun Screen() {
    // Create the ViewModel and retrieve the state values using asState
    val viewModel = viewModel<DataViewModel>()
    val customObject by viewModel.uiState.collectAsState()
    val widget1State by viewModel.widget1StateFlow.collectAsState()
    val widget2State by viewModel.widget2StateFlow.collectAsState()

    // Create a TabRow to switch between tabs
    var selectedTabIndex by remember { mutableStateOf(0) }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = Color.LightGray,
            contentColor = Color.White
        ) {
            Tab(
                selected = selectedTabIndex == 0,
                onClick = { selectedTabIndex = 0 }
            ) {
                Icon(Icons.Default.AccountBox, contentDescription = "Tab 1")
                Text(text = "Tab 1")
            }
            Tab(
                selected = selectedTabIndex == 1,
                onClick = { selectedTabIndex = 1 }
            ) {
                Icon(Icons.Default.Settings, contentDescription = "Tab 2")
                Text(text = "Tab 2")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Show the content based on the selected tab
        when (selectedTabIndex) {
            0 -> Tab1Content(customObject, widget1State, widget2State, viewModel)
            1 -> Tab2Content(widget2State, viewModel)
        }
    }
}

@Composable
fun Tab1Content(
    customObject: DataUIState,
    widget1State: Widget1State,
    widget2State: Widget2State,
    viewModel: DataViewModel
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Widget1(widget1State) { viewModel.updateWidget1State() }
        Spacer(modifier = Modifier.height(16.dp))
        if (widget1State == Widget1State.ON) {
            Widget2(widget2State) { viewModel.updateWidget2State() }
            Spacer(modifier = Modifier.height(16.dp))
        }
        MultiSlider1(
            minValue = -10f,
            maxValue = 10f,
            step = 0.1f,
            value = customObject.characteristic3,
            onValueChange = { viewModel.onCharacteristic3Change(it) },
            enabled = widget2State == Widget2State.OFF
        )
    }
}

@Composable
fun Tab2Content(
    widget2State: Widget2State,
    viewModel: DataViewModel
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Widget2(widget2State) { viewModel.updateWidget2State() }
        Spacer(modifier = Modifier.height(16.dp))
        if (widget2State == Widget2State.ON) {
            MultiSlider2(
                minValue = 0f,
                maxValue = 10f,
                step = 1f,
                value1 = 0f,
                value2 = 1f,
                onValueChange1 = {},
                onValueChange2 = {},
                enabled = true
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        // TODO: Add other widgets for Tab 2
    }
}


@Composable
fun Widget1(
    widget1State: Widget1State,
    onToggle: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Widget1")
        Switch(checked = widget1State == Widget1State.ON, onCheckedChange = { onToggle() })
    }
}

@Composable
fun Widget2(
    widget2State: Widget2State,
    onToggle: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Widget2")
        Switch(checked = widget2State == Widget2State.ON, onCheckedChange = { onToggle() })
    }
}

@Composable
fun MultiSlider1(
    minValue: Float,
    maxValue: Float,
    step: Float,
    value: Float,
    onValueChange: (Float) -> Unit,
    enabled: Boolean
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "MultiSlider1")
        Slider(
            value = value,
            onValueChange = onValueChange,
            valueRange = minValue..maxValue,
            steps = ((maxValue - minValue) / step).toInt(),
            enabled = enabled
        )
    }
}

@Composable
fun MultiSlider2(
    minValue: Float,
    maxValue: Float,
    step: Float,
    value1: Float,
    value2: Float,
    onValueChange1: (Float) -> Unit,
    onValueChange2: (Float) -> Unit,
    enabled: Boolean
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "MultiSlider2")
        Row(verticalAlignment = Alignment.CenterVertically) {
            Slider(
                value = value1,
                onValueChange = onValueChange1,
                valueRange = minValue..maxValue,
                steps = ((maxValue - minValue) / step).toInt(),
                enabled = enabled
            )
            Slider(
                value = value2,
                onValueChange = onValueChange2,
                valueRange = minValue..maxValue,
                steps = ((maxValue - minValue) / step).toInt(),
                enabled = enabled
            )
        }
    }
}

// Enums for widget states
enum class Widget1State {
    OFF, ON
}

enum class Widget2State {
    OFF, ON
}


