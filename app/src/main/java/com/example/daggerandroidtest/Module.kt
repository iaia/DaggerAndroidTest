package com.example.daggerandroidtest

import dagger.Component
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module
class AppModule {
}

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}

@Singleton
@Component(
    modules = [
        AppModule::class,
        ActivityModule::class,
        AndroidInjectionModule::class
    ]
)
interface AppComponent : AndroidInjector<MyApplication> {
    @Component.Factory
    interface Factory : AndroidInjector.Factory<MyApplication>
}
