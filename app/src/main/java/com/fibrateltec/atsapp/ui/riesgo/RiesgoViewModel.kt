package com.fibrateltec.navbar.ui.riesgo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RiesgoViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is riesgo"
    }
    val text: LiveData<String> = _text
}