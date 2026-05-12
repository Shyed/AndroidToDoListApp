/*
=====================================================
 PROJECT: Android To Do List App (OrgMe)
 MODULE: ui/MainActivity
 AUTHOR: Sheila Demonteverde
 DATE: 05/28/2020
 Updated Dev Notes: 05/12/2026

 DESCRIPTION:
 This activity serves as the main entry point
 for the OrgMe application.

 FEATURES:
 - Navigation Controller setup
 - Toolbar integration
 - Fragment navigation support
 - Back navigation handling

 NOTES:
 - Uses Android Navigation Component
 - Hosts navigation fragments
 - Controls application toolbar behavior
=====================================================
*/

package com.sheilademonteverde.orgme.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.sheilademonteverde.orgme.R
import kotlinx.android.synthetic.main.activity_main.*

/*-- MAIN ACTIVITY CLASS --*/
class MainActivity : AppCompatActivity() 
{

    /*-- NAVIGATION CONTROLLER --*/
    private lateinit var navController: NavController

    /*-- ACTIVITY CREATION --*/
    override fun onCreate(savedInstanceState: Bundle?) 
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Load activity layout
        setSupportActionBar(toolbar) // Set toolbar as action bar

        /*-- INITIALIZE NAVIGATION CONTROLLER --*/
        navController = Navigation.findNavController(
            this,
            R.id.nav_host_fragment
        )

    }

    /*-- HANDLE BACK BUTTON PRESS: Return toolbar title to default screen title --*/
    override fun onBackPressed() 
    {
        super.onBackPressed()
        toolbar.title = getString(R.string.Listmaker)
    }
}
