package com.example.daggerandroidtest

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.After
import org.junit.Before
import org.junit.Test

class MainActivityTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun shouldShowHelloWorld() {
        onView(withId(R.id.tv_hello_world)).check(matches(withText("Hello World!")))
        onView(withId(R.id.tv_hello_world)).check(matches(withText("Hello World?")))
    }
}
