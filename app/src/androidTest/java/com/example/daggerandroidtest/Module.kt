package com.example.daggerandroidtest

import androidx.lifecycle.ViewModelProvider
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import io.mockk.MockKSettings.relaxed
import io.mockk.mockk
import javax.inject.Named
import javax.inject.Singleton

@Module
class TestAppModule {
    @Provides
    @Named("applicationText")
    fun provideApplicationText(application: MyTestApplication) = application.applicationText
}

@Module
abstract class TestActivityModule {
    @ContributesAndroidInjector()
    abstract fun contributeMainActivity(): MainActivity
}

@Module
object TestViewModelFactoryModule {
    val viewModelFactory = mockk<ViewModelProvider.Factory>(relaxed = true)

    @Provides
    fun provideViewModelFactory(): ViewModelProvider.Factory {
        return viewModelFactory
    }
}

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        TestAppModule::class,
        TestActivityModule::class,
        TestViewModelFactoryModule::class
    ]
)
interface TestAppComponent : AndroidInjector<MyTestApplication> {
    @Component.Factory
    interface Factory : AndroidInjector.Factory<MyTestApplication> {
        override fun create(
            @BindsInstance application: MyTestApplication
        ): TestAppComponent
    }
}
