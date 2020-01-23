package com.example.daggerandroidtest

import androidx.lifecycle.MutableLiveData

class MainActivityViewModel {
    val text = MutableLiveData<String>("hello world?")
}
