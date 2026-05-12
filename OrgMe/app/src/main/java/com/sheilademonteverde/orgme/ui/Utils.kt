/*
=====================================================
 PROJECT: Android To Do List App (OrgMe)
 MODULE: ui/Utils
 AUTHOR: Sheila Demonteverde
 DATE: 05/28/2020
 Updated Dev Notes: 05/12/2026

 DESCRIPTION:
 This utility object provides helper functions
 used throughout the application.

 FEATURES:
 - Date formatting utilities
 - Readable date conversion
 - Shared helper methods

 NOTES:
 - Centralizes reusable utility functions
 - Improves code organization
=====================================================
*/

package com.sheilademonteverde.orgme.ui

import java.text.SimpleDateFormat
import java.util.*

/*-- UTILITY OBJECT --*/
object Utils 
{

     /*-- FORMAT READABLE DATE: Converts timestamp values into human-readable date and time strings --*/
    fun getReadableDate(d: Long): String 
    {
        // Define display date format
        val mFormat = SimpleDateFormat("EEEE d,MMM yyyy ' at ' h:mm a", Locale.ENGLISH)
        
        // Return formatted date string
        return mFormat.format(d)
    }


}
