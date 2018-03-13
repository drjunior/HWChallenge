package com.hostelworld.challenge.ui.properties

import android.content.Intent
import android.support.test.espresso.Espresso
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.hostelworld.challenge.R
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * PropertiesListActivity Ui tests
 */
@RunWith(AndroidJUnit4::class)
class PropertiesListActivityTest {

    @get:Rule
    val propertiesActivityRule = ActivityTestRule<PropertiesListActivity>(PropertiesListActivity::class.java, true, false)

    @Before
    fun setUp() {
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun propertiesList_ValidVisibility() {
        val intent = Intent()
        propertiesActivityRule.launchActivity(intent)

        Espresso.onView(ViewMatchers.withId(R.id.toolbar)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.rv_properties_list)).check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
    }

}