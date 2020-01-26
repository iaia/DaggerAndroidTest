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
class AppModule {
    @Provides
    @Named("applicationText")
    fun provideApplicationText(application: MyTestApplication) = application.applicationText
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

class ViewModelFactory @Inject constructor(
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
        AppModule::class,
        ActivityModule::class,
        ViewModelFactoryModule::class
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
