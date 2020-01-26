package com.example.daggerandroidtest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.daggerandroidtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val app = application as MyApplication
    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(
            this, R.layout.activity_main
        )
    }
    private val viewModel by lazy { MainActivityViewModel(app.applicationText) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
    }
}
