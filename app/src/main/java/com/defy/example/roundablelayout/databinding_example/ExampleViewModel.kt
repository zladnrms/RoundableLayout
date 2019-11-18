package com.defy.example.roundablelayout.databinding_example

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel

class ExampleViewModel: ViewModel() {

    /** MVVM + Databinding Example */

    val blackOn = ObservableBoolean(false)

    fun toBlack(value: Boolean) {
        blackOn.set(value)
    }
}