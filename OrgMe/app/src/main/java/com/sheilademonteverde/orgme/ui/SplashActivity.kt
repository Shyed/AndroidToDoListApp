/*
=====================================================
 PROJECT: Android To Do List App (OrgMe)
 MODULE: ui/SplashActivity.kt
 AUTHOR: Sheila Demonteverde
 DATE: 05/28/2020
 Updated Dev Notes: 05/12/2026

 DESCRIPTION:
 This activity displays the application's
 splash screen during startup.

 FEATURES:
 - Splash screen display
 - Delayed activity transition
 - Automatic navigation to MainActivity
 - Hidden ActionBar

 NOTES:
 - Uses Handler for delayed execution
 - Splash screen duration is 3 seconds
=====================================================
*/
package com.sheilademonteverde.orgme.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.sheilademonteverde.orgme.R

/*-- TIME CONSTANTS --*/
private const val ONE_SECOND: Long = 1000 // 1 second equals 1000 milliseconds

// Splash screen display duration. MainActivity starts after 3 seconds
private const val SPLASH_TIME_OUT: Long = 3 * ONE_SECOND

/*-- SPLASH ACTIVITY CLASS --*/
class SplashActivity : AppCompatActivity() 
{

    /*-- ACTIVITY CREATION --*/
    override fun onCreate(savedInstanceState: Bundle?) 
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash) // Load splash screen layout

        supportActionBar?.hide() // Hide ActionBar for fullscreen appearance

        /*-- DELAY MAIN ACTIVITY LAUNCH --*/
        Handler().postDelayed(
            {

            // Execute after splash timer ends. Launch MainActivity                 
            startActivity(Intent(this, MainActivity::class.java))

            // Close SplashActivity
            finish()
        }, SPLASH_TIME_OUT)
    }
}
