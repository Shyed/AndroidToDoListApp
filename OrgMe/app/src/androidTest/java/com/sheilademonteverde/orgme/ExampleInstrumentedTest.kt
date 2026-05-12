/*
=====================================================
 PROJECT: Android To Do List App
 FILE: ExampleInstrumentedTest.kt

 DESCRIPTION:
 This file contains instrumented tests for the
 Android application.

 Instrumented tests run on:
 - physical Android devices
 - Android emulators

 FEATURES:
 - Application context testing
 - Package name verification
 - AndroidJUnit4 test runner integration

 NOTES:
 - Used for Android testing validation
 - Confirms application context is correct
=====================================================
*/

package com.sheilademonteverde.orgme

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/*-- ANDROID TEST RUNNER --*/
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest 
{
    /*-- APPLICATION CONTEXT TEST --*/
    @Test
    fun useAppContext() 
    {
        // Retrieve application context
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        // Verify correct package name
        assertEquals("com.sheilademonteverde.orgme", appContext.packageName)
    }
}
