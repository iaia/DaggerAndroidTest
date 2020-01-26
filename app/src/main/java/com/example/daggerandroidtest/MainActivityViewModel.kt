package com.example.daggerandroidtest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel(
    val appText: String
) : ViewModel() {
    val text = MutableLiveData<String>("hello world?")
}
