package com.example.daggerandroidtest

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.BindsInstance
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

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ActivityModule::class,
        ViewModelFactoryModule::class
    ]
)
interface AppComponent : AndroidInjector<MyApplication> {
    @Component.Factory
    interface Factory : AndroidInjector.Factory<MyApplication> {
        override fun create(
            @BindsInstance application: MyApplication
        ): AppComponent
    }
}
