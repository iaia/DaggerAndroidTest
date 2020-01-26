package com.example.daggerandroidtest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.*
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import javax.inject.Inject
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
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}

@Module
abstract class TestViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: TestViewModelFactory): ViewModelProvider.Factory
}

class TestViewModelFactory @Inject constructor(
    @Named("applicationText")
    private val applicationText: String
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainActivityViewModel(applicationText) as T
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
