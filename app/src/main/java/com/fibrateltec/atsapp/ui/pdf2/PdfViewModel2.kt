package com.fibrateltec.atsapp.ui.pdf2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PdfViewModel2 : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is pdf2"
    }
    val text: LiveData<String> = _text
}