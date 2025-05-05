/*
Project Name: OrgMe
Author: Sheila Demonteverde
Module Name: repositories/LocalRepositoryImpl
Date: 05/28/2020
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
 * LocalRepositoryImpl : This class is responsible for managing local database
 * It performs the functions specified in the DAO
 */
class LocalRepositoryImpl private constructor() {

    companion object {

        // private instance of class to provide singleton pattern implementation
        private var INSTANCE: LocalRepositoryImpl? = null

        // database instance
        private lateinit var database: LocalDatabase

        // for running insert operation on different thread than UI
        private lateinit var executor: ExecutorService

        // database dao
        private lateinit var localRepositoryDao: LocalRepositoryDao

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = LocalRepositoryImpl()
                // creating room database instance
                database = Room.databaseBuilder(
                    context.applicationContext,
                    LocalDatabase::class.java,
                    DatabaseConstants.databaseName
                ).build()
                localRepositoryDao = database.getDao()
                executor = Executors.newSingleThreadExecutor()
            }
        }

        // getter for the LocalRepository class
        fun get(): LocalRepositoryImpl {
            return INSTANCE
                ?: throw IllegalStateException("LocalRepositoryImpl must be initialized first.")
        }
    }

    fun addList(model: ListModel) = localRepositoryDao.addList(model)

    fun getAllLists(): LiveData<List<ListModel>> {
        return localRepositoryDao.getAllLists()
    }

    fun addListItem(
        name: String,
        dueDate: Long,
        itemListId: Int
    ) {
        executor.execute {
            val model = ListItemModel(
                name,
                itemListId,
                dueDate
            )
            localRepositoryDao.addItemToList(model)
        }
    }

    fun getAllItemsOfList(listId: Int): LiveData<List<ListItemModel>> {
        return localRepositoryDao.getAllItemsOfList(listId)
    }

    fun getListItemByItemId(listItemId: Int): LiveData<ListItemModel?> {
        return localRepositoryDao.getListItemByItemId(listItemId)
    }

    fun getListById(listId: Int): LiveData<ListModel?> {
        return localRepositoryDao.getListById(listId)
    }

    fun updateListItem(
        name: String,
        dueDate: Long,
        itemId: Int
    ) {
        executor.execute {
            localRepositoryDao.updateListItem(name, dueDate, itemId)
        }
    }

    fun deleteListItem(itemId: Int) {
        executor.execute {
            localRepositoryDao.deleteListItem(itemId)
        }
    }

    fun deleteList(listId: Int) {
        executor.execute {
            localRepositoryDao.deleteListItemsByListId(listId)
            localRepositoryDao.deleteList(listId)
        }
    }

    fun updateList(
        name: String,
        listId: Int
    ) {
        executor.execute {
            localRepositoryDao.updateList(name, listId)
        }
    }

}