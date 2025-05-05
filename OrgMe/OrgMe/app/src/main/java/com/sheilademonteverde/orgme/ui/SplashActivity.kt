/*
Project Name: OrgMe
Author: Sheila Demonteverde
Module Name: SplashActivity.kt
Date: 05/28/2020
*/

package com.sheilademonteverde.orgme.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.sheilademonteverde.orgme.R

// 1 sec is equal to 1000 milliseconds
private const val ONE_SECOND: Long = 1000

// This is the loading time of the splash screen
// 3 seconds for splash screen to stay and then main activity will start
private const val SPLASH_TIME_OUT: Long = 3 * ONE_SECOND

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()

        Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start your app main activity

            startActivity(Intent(this, MainActivity::class.java))

            // close this activity
            finish()
        }, SPLASH_TIME_OUT)
    }
}
