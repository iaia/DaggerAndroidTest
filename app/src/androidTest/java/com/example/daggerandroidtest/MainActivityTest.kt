package com.example.daggerandroidtest

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()

    //@MockK
    lateinit var viewModel: MainActivityViewModel

    @Before
    fun setUp() {
        // MockKAnnotations.init(this)
        val appText = "HOI"
        viewModel = MainActivityViewModel(appText)
        viewModel.text.postValue("Hello World!")
        /*
        val viewModelText = MutableLiveData("Hello World!")
        every { viewModel.appText } returns appText
        every { viewModel.text } returns viewModelText
         */
    }

    @After
    fun tearDown() {
    }

    @Test
    fun shouldShowHelloWorld() {
        onView(withId(R.id.tv_hello_world)).check(matches(withText("Hello World!")))
        onView(withId(R.id.tv_app_text)).check(matches(withText("HOI")))
    }
}
