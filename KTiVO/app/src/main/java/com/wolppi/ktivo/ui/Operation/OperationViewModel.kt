package com.wolppi.ktivo.ui.Operation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OperationViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value =  "it's Coming !!!"
    }
    val text: LiveData<String> = _text
}