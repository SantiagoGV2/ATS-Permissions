package com.fibrateltec.atsapp.ui.pdf4

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PdfViewModel4 : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is pdf3"
    }
    val text: LiveData<String> = _text
}