/*
Project Name: OrgMe
Author: Sheila Demonteverde
Module Name: App
Date: 05/28/2020
*/

package com.sheilademonteverde.orgme.ui

import android.app.Application
import com.sheilademonteverde.orgme.repositories.LocalRepositoryImpl

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        LocalRepositoryImpl.initialize(this)

    }

}