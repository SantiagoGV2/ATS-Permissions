package com.fibrateltec.atsapp.ui.riesgo2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RiesgoViewModel2 : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is riesgo"
    }
    val text: LiveData<String> = _text
}