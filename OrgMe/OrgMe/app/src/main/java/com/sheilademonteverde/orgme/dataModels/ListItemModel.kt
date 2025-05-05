/*
Project Name: OrgMe
Author: Sheila Demonteverde
Module Name: dataModels/ListItemModel
Date: 05/28/2020
*/

package com.sheilademonteverde.orgme.dataModels

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sheilademonteverde.orgme.repositories.DatabaseConstants

@Entity(tableName = DatabaseConstants.tableListItems)
data class ListItemModel(
    val name: String,
    val itemListId: Int,
    val dueDate: Long
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}