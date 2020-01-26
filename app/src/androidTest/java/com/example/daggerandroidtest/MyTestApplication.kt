package com.example.daggerandroidtest

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject


class MyTestApplication : MyApplication() {
    override val applicationText = "FUGA"

    @Inject
    override lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> {
        DaggerTestAppComponent.factory().create(this).inject(this)
        return dispatchingAndroidInjector
    }
}

class MyTestRunner : AndroidJUnitRunner() {
    @Throws(
        InstantiationException::class,
        IllegalAccessException::class,
        ClassNotFoundException::class
    )
    override fun newApplication(
        cl: ClassLoader,
        className: String,
        context: Context
    ): Application {
        return super.newApplication(cl, MyTestApplication::class.java.name, context)
    }
}
