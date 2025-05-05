/*
Project Name: OrgMe
Author: Sheila Demonteverde
Module Name: repositories/LocalDatabase
Date: 05/28/2020
*/

package com.sheilademonteverde.orgme.repositories

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sheilademonteverde.orgme.dataModels.ListItemModel
import com.sheilademonteverde.orgme.dataModels.ListModel

/*
 * LocalDatabase - Abstract class for extending the Room database
 * We have also mentioned the database tables ( data model classes ) and database version
 */
@Database(entities = [ListModel::class, ListItemModel::class], version = 1, exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun getDao(): LocalRepositoryDao
}