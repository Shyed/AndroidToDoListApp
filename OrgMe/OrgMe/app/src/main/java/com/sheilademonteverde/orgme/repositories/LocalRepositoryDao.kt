/*
Project Name: OrgMe
Author: Sheila Demonteverde
Module Name: repositories/DatabaseConstants
Date: 05/28/2020
*/

package com.sheilademonteverde.orgme.repositories

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sheilademonteverde.orgme.dataModels.ListItemModel
import com.sheilademonteverde.orgme.dataModels.ListModel

/*
 * Data Access Object interface for Room Database
 */
@Dao
interface LocalRepositoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addList(model: ListModel): Long

    @Query("Select * from ${DatabaseConstants.tableList} order by id DESC")
    fun getAllLists(): LiveData<List<ListModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addItemToList(model: ListItemModel)

    @Query("Select * from ${DatabaseConstants.tableListItems} where itemListId = :listId order by id DESC")
    fun getAllItemsOfList(listId: Int): LiveData<List<ListItemModel>>

    @Query("update " + DatabaseConstants.tableListItems + " set name = :name,dueDate = :dueDate where id = :itemId")
    fun updateListItem(name: String, dueDate: Long, itemId: Int)

    @Query("Delete from " + DatabaseConstants.tableListItems + " where id = :itemId")
    fun deleteListItem(itemId: Int)

    @Query("Delete from " + DatabaseConstants.tableListItems + " where itemListId = :listId")
    fun deleteListItemsByListId(listId: Int)

    @Query("Delete from " + DatabaseConstants.tableList + " where id = :listId")
    fun deleteList(listId: Int)

    @Query("SELECT * FROM ${DatabaseConstants.tableList} WHERE id= :id")
    fun getListById(id: Int): LiveData<ListModel?>

    @Query("SELECT * FROM ${DatabaseConstants.tableListItems} WHERE id= :id")
    fun getListItemByItemId(id: Int): LiveData<ListItemModel?>

    @Query("update " + DatabaseConstants.tableList + " set name = :name where id = :listId")
    fun updateList(name: String, listId: Int)

}