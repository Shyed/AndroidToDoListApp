/*
=====================================================
 PROJECT: Android To Do List App (OrgMe)
 MODULE: ui/App
 AUTHOR: Sheila Demonteverde
 DATE: 05/28/2020
 Updated Dev Notes: 05/12/2026

 DESCRIPTION:
 This Application class initializes global
 application-level components when the app starts.

 FEATURES:
 - Initializes local repository
 - Provides application-wide setup
 - Configures Room database access

 NOTES:
 - Runs before any activity is launched
 - Used for application-wide initialization
=====================================================
*/

package com.sheilademonteverde.orgme.ui

import android.app.Application
import com.sheilademonteverde.orgme.repositories.LocalRepositoryImpl

/*-- APPLICATION CLASS --*/
class App : Application() 
{

    override fun onCreate() 
    {
        super.onCreate()

        LocalRepositoryImpl.initialize(this) // Initialize local repository and database

    }

}
