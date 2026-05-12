/*
=====================================================
 PROJECT: Android To Do List App (OrgMe)
 MODULE: repositories/LocalDatabase
 AUTHOR: Sheila Demonteverde
 DATE: 05/28/2020
 Updated Dev Notes: 05/12/2026

 DESCRIPTION:
 This abstract class defines the Room database
 configuration for the OrgMe application.

 FEATURES:
 - Registers Room database entities
 - Defines database version
 - Provides DAO access methods
 - Extends RoomDatabase

 NOTES:
 - Central database entry point for Room
 - Manages local SQLite database operations
=====================================================
*/

package com.sheilademonteverde.orgme.repositories

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sheilademonteverde.orgme.dataModels.ListItemModel
import com.sheilademonteverde.orgme.dataModels.ListModel

/*-- ROOM DATABASE CONFIGURATION --*/

/*
 * LocalDatabase:
 * Abstract Room database class that connects application entities and DAO interfaces.
 */
@Database(entities = [ListModel::class, ListItemModel::class], version = 1, exportSchema = false)

/*-- LOCAL DATABASE CLASS --*/
abstract class LocalDatabase : RoomDatabase() 
{
    /*-- DAO ACCESS METHOD --*/
    abstract fun getDao(): LocalRepositoryDao // Provides access to database operations
}
