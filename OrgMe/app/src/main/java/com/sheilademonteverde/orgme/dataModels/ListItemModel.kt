/*
=====================================================
 PROJECT: Android To Do List App (OrgMe)
 MODULE: dataModels/ListItemModel
 AUTHOR: Sheila Demonteverde
 DATE: 05/28/2020
 Updated Dev Notes: 05/12/2026

 DESCRIPTION:
 This data model represents individual task items
 stored in the application's Room database.

 FEATURES:
 - Stores task name
 - Stores related task list ID
 - Stores due date information
 - Uses Room Entity annotations
 - Auto-generates primary key values

 NOTES:
 - Part of Room database architecture
 - Used for task persistence and retrieval
=====================================================
*/

package com.sheilademonteverde.orgme.dataModels

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sheilademonteverde.orgme.repositories.DatabaseConstants

/*-- ROOM DATABASE ENTITY --*/
@Entity(tableName = DatabaseConstants.tableListItems)

/*-- TASK ITEM DATA MODEL --*/
data class ListItemModel(
    val name: String, // Task name/title
    val itemListId: Int, // Related task list identifier
    val dueDate: Long // Task due date stored as Long timestamp
) 
{
    /*-- PRIMARY KEY --*/
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0  // Automatically generated database ID
}
