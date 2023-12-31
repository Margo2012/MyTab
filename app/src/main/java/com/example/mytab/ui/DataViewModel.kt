package com.example.mytab.ui

import androidx.lifecycle.ViewModel
import com.example.mytab.data.DataUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DataViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(DataUIState())
    val uiState: StateFlow<DataUIState> = _uiState.asStateFlow()

    private val _widget1StateFlow = MutableStateFlow(Widget1State.OFF)
    val widget1StateFlow = _widget1StateFlow.asStateFlow()

    private val _widget2StateFlow = MutableStateFlow(Widget2State.OFF)
    val widget2StateFlow = _widget2StateFlow.asStateFlow()

    private val _sliderPosition1  = MutableStateFlow(0f)
    var sliderPosition1 = _sliderPosition1

    private val _sliderPosition2  = MutableStateFlow(0f)
    var sliderPosition2 = _sliderPosition2

    private val _sliderPosition3  = MutableStateFlow(0f)
    var sliderPosition3 = _sliderPosition3



    // Functions to update the state
    fun updateWidget1State() {
        _widget1StateFlow.value = if (_widget1StateFlow.value == Widget1State.OFF) {
            Widget1State.ON
        } else {
            Widget1State.OFF
        }

        // Update the characteristics based on the new state
        if (_widget1StateFlow.value == Widget1State.ON){
            _uiState.value.characteristic4 =
                _uiState.value.characteristic1
            _uiState.value.characteristic5 =
                _uiState.value.characteristic1
        }
    }
    fun updateWidget2State(){
        _widget2StateFlow.value = if (_widget2StateFlow.value == Widget2State.OFF){
            Widget2State.ON
        } else{
            Widget2State.OFF
        }

        if (_widget2StateFlow.value == Widget2State.OFF){
            _uiState.value.characteristic1 = maxOf(_uiState.value.characteristic3, _uiState.value.characteristic4)
        }
    }

    fun onCharacteristic3Change(value: Float) {
        _uiState.value.characteristic3 = value
        if (widget2StateFlow.value == Widget2State.OFF) {
            _uiState.value.characteristic1 =
                maxOf( _uiState.value.characteristic3,  _uiState.value.characteristic4)
        }
    }

    fun onCharacteristic4Change(value: Float) {
        // Update characteristic4 and characteristic1 based on Widget2 state
        _uiState.value.characteristic4 = value
        if (widget2StateFlow.value == Widget2State.OFF) {
            _uiState.value.characteristic1 =
                maxOf(_uiState.value.characteristic3, _uiState.value.characteristic4)
        }
    }

    fun onCharacteristic5Change(value: Float) {
        // Update characteristic5 and characteristic1 based on Widget2 state
        _uiState.value.characteristic5 = value
        if (widget2StateFlow.value == Widget2State.OFF) {
            _uiState.value.characteristic1 =
                maxOf(_uiState.value.characteristic3, _uiState.value.characteristic4)
        }
    }
}


