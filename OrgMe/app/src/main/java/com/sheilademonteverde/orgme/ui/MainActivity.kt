/*
Project Name: OrgMe
Author: Sheila Demonteverde
Module Name: MainActivity
Date: 05/28/2020
*/

package com.sheilademonteverde.orgme.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.sheilademonteverde.orgme.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        navController = Navigation.findNavController(
            this,
            R.id.nav_host_fragment
        )

    }

    //When android back option is pressed return to To-Do Category List
    override fun onBackPressed() {
        super.onBackPressed()
        toolbar.title = getString(R.string.Listmaker)
    }
}
