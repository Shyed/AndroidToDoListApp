/*
Project Name: OrgMe
Author: Sheila Demonteverde
Module Name: dataModels/ListModel
Date: 05/28/2020
*/

package com.sheilademonteverde.orgme.dataModels

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sheilademonteverde.orgme.repositories.DatabaseConstants

@Entity(tableName = DatabaseConstants.tableList)
data class ListModel(
    val name: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}