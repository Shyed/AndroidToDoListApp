/*
=====================================================
 PROJECT: Android To Do List App (OrgMe)
 MODULE: repositories/DatabaseConstants
 AUTHOR: Sheila Demonteverde
 DATE: 05/28/2020
 Updated Dev Notes: 05/12/2026

 DESCRIPTION:
 This object stores database-related constants
 used throughout the application.

 FEATURES:
 - Centralized database name storage
 - Centralized table name storage
 - Prevents repeated hardcoded values

 NOTES:
 - Improves code maintainability
 - Used by Room database components
=====================================================
*/

package com.sheilademonteverde.orgme.repositories

/*-- DATABASE CONSTANTS OBJECT --*/
object DatabaseConstants 
{
    /*-- DATABASE NAME --*/
    const val databaseName = "ListMakerDatabase12345" // Main Room database name

    /*-- TABLE NAMES --*/
    const val tableList = "tableList" // Table storing task lists
    const val tableListItems = "tableListItems" // Table storing task items

}
