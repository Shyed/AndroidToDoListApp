/*
=====================================================
 PROJECT: Android To Do List App (OrgMe)
 MODULE: dataModels/ListModel
 AUTHOR: Sheila Demonteverde
 DATE: 05/28/2020
 Updated Dev Notes: 05/12/2026

 DESCRIPTION:
 This data model represents a task list entity
 stored in the Room database.

 FEATURES:
 - Stores task list name
 - Uses Room Entity annotations
 - Auto-generates primary key values

 NOTES:
 - Part of Room database architecture
 - Used for organizing groups of tasks
=====================================================
*/

package com.sheilademonteverde.orgme.dataModels

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sheilademonteverde.orgme.repositories.DatabaseConstants

/*-- ROOM DATABASE ENTITY --*/
@Entity(tableName = DatabaseConstants.tableList)

/*-- TASK LIST DATA MODEL --*/
data class ListModel(
    val name: String // Name of the task list
) 
{
    /*-- PRIMARY KEY --*/
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0 // Automatically generated database ID
}
