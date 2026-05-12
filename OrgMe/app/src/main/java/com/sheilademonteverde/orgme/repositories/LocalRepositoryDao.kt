/*
=====================================================
 PROJECT: Android To Do List App (OrgMe)
 MODULE: repositories/LocalRepositoryDao
 AUTHOR: Sheila Demonteverde
 DATE: 05/28/2020
 Updated Dev Notes: 05/12/2026

 DESCRIPTION:
 This interface defines database operations
 for the Room persistence library.

 FEATURES:
 - Insert task lists
 - Insert task items
 - Retrieve task lists
 - Retrieve task items
 - Update tasks and lists
 - Delete tasks and lists
 - LiveData integration

 NOTES:
 - Uses Room DAO annotations
 - Handles CRUD database operations
=====================================================
*/

package com.sheilademonteverde.orgme.repositories

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sheilademonteverde.orgme.dataModels.ListItemModel
import com.sheilademonteverde.orgme.dataModels.ListModel

/*-- ROOM DAO INTERFACE --*/

/*
 * LocalRepositoryDao:
 * Handles database queries and operations for task lists and task items.
 */

@Dao
interface LocalRepositoryDao 
{

     /*-- INSERT TASK LIST --*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addList(model: ListModel): Long

    /*-- GET ALL TASK LISTS --*/
    @Query("Select * from ${DatabaseConstants.tableList} order by id DESC")
    fun getAllLists(): LiveData<List<ListModel>>

    /*-- INSERT TASK ITEM --*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addItemToList(model: ListItemModel)

     /*-- GET TASK ITEMS BY LIST ID --*/
    @Query("Select * from ${DatabaseConstants.tableListItems} where itemListId = :listId order by id DESC")
    fun getAllItemsOfList(listId: Int): LiveData<List<ListItemModel>>

    /*-- UPDATE TASK ITEM --*/
    @Query("update " + DatabaseConstants.tableListItems + " set name = :name,dueDate = :dueDate where id = :itemId")
    fun updateListItem(name: String, dueDate: Long, itemId: Int)

    /*-- DELETE TASK ITEM --*/
    @Query("Delete from " + DatabaseConstants.tableListItems + " where id = :itemId")
    fun deleteListItem(itemId: Int)

     /*-- DELETE TASK ITEMS BY LIST ID --*/
    @Query("Delete from " + DatabaseConstants.tableListItems + " where itemListId = :listId")
    fun deleteListItemsByListId(listId: Int)

    /*-- DELETE TASK LIST --*/
    @Query("Delete from " + DatabaseConstants.tableList + " where id = :listId")
    fun deleteList(listId: Int)

    /*-- GET TASK LIST BY ID --*/
    @Query("SELECT * FROM ${DatabaseConstants.tableList} WHERE id= :id")
    fun getListById(id: Int): LiveData<ListModel?>

     /*-- GET TASK ITEM BY ITEM ID --*/
    @Query("SELECT * FROM ${DatabaseConstants.tableListItems} WHERE id= :id")
    fun getListItemByItemId(id: Int): LiveData<ListItemModel?>

    /*-- UPDATE TASK LIST --*/
    @Query("update " + DatabaseConstants.tableList + " set name = :name where id = :listId")
    fun updateList(name: String, listId: Int)

}
