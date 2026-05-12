/*
=====================================================
 PROJECT: Android To Do List App (OrgMe)
 MODULE: repositories/LocalRepositoryImpl
 AUTHOR: Sheila Demonteverde
 DATE: 05/28/2020
 Updated Dev Notes: 05/12/2026

 DESCRIPTION:
 This repository class manages local database
 operations for the OrgMe application.

 FEATURES:
 - Room database initialization
 - Singleton pattern implementation
 - CRUD database operations
 - Background thread execution
 - LiveData integration

 NOTES:
 - Acts as intermediary between ViewModels and DAO
 - Uses ExecutorService for background tasks
=====================================================
*/

package com.sheilademonteverde.orgme.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.sheilademonteverde.orgme.dataModels.ListItemModel
import com.sheilademonteverde.orgme.dataModels.ListModel
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/*
 * LocalRepositoryImpl:
 * Handles local Room database operations and executes DAO functions.
 */
class LocalRepositoryImpl private constructor() 
{
    companion object 
    {
        /*-- SINGLETON INSTANCE --*/ 
        private var INSTANCE: LocalRepositoryImpl? = null // Singleton repository instance

        /*-- DATABASE VARIABLES --*/
        private lateinit var database: LocalDatabase // Room database instance   
        private lateinit var executor: ExecutorService // Background thread executor 
        private lateinit var localRepositoryDao: LocalRepositoryDao // DAO interface instance

        /*-- INITIALIZE DATABASE --*/
        fun initialize(context: Context) 
        {
            if (INSTANCE == null) 
            {
                INSTANCE = LocalRepositoryImpl()

                // Create Room database instance
                database = Room.databaseBuilder(
                    context.applicationContext,
                    LocalDatabase::class.java,
                    DatabaseConstants.databaseName
                ).build()
                localRepositoryDao = database.getDao() // Retrieve DAO instance
                executor = Executors.newSingleThreadExecutor() // Create background thread executor
            }
        }

         /*-- GET REPOSITORY INSTANCE --*/
        fun get(): LocalRepositoryImpl 
        {
            return INSTANCE
                ?: throw IllegalStateException("LocalRepositoryImpl must be initialized first.")
        }
    }

    /*-- ADD TASK LIST --*/
    fun addList(model: ListModel) = localRepositoryDao.addList(model)

    /*-- GET ALL TASK LISTS --*/
    fun getAllLists(): LiveData<List<ListModel>> 
    {
        return localRepositoryDao.getAllLists()
    }

    /*-- ADD TASK ITEM --*/
    fun addListItem(
        name: String,
        dueDate: Long,
        itemListId: Int
    ) 
    {
        executor.execute 
        {
            // Create task item model
            val model = ListItemModel(
                name,
                itemListId,
                dueDate
            )
            // Insert item into database
            localRepositoryDao.addItemToList(model)
        }
    }

    /*-- GET ALL TASK ITEMS OF LIST --*/
    fun getAllItemsOfList(listId: Int): LiveData<List<ListItemModel>> 
    {
        return localRepositoryDao.getAllItemsOfList(listId)
    }

    /*-- GET TASK ITEM BY ID --*/
    fun getListItemByItemId(listItemId: Int): LiveData<ListItemModel?> 
    {
        return localRepositoryDao.getListItemByItemId(listItemId)
    }

    /*-- GET TASK LIST BY ID --*/
    fun getListById(listId: Int): LiveData<ListModel?> 
    {
        return localRepositoryDao.getListById(listId)
    }

    /*-- UPDATE TASK ITEM --*/
    fun updateListItem(
        name: String,
        dueDate: Long,
        itemId: Int
    ) 
    {
        executor.execute 
        {
            localRepositoryDao.updateListItem(name, dueDate, itemId)
        }
    }

    /*-- DELETE TASK ITEM --*/
    fun deleteListItem(itemId: Int) 
    {
        executor.execute 
        {
            localRepositoryDao.deleteListItem(itemId)
        }
    }

    /*-- DELETE TASK LIST --*/
    fun deleteList(listId: Int) 
    {
        executor.execute 
        {
            localRepositoryDao.deleteListItemsByListId(listId) // Delete all items related to list
            localRepositoryDao.deleteList(listId) // Delete task list
        }
    }

    /*-- UPDATE TASK LIST --*/
    fun updateList(
        name: String,
        listId: Int
    ) 
    {
        executor.execute 
        {
            localRepositoryDao.updateList(name, listId)
        }
    }

}
