package com.example.daggerandroidtest

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

open class MyApplication : Application(), HasAndroidInjector {
    open val applicationText = "hoge"
    @Inject
    open lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> {
        DaggerAppComponent.factory().create(this).inject(this)
        return dispatchingAndroidInjector
    }
}

