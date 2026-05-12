/*
=====================================================
 PROJECT: Android To Do List App (OrgMe)
 MODULE: viewModels/ListFragmentViewModel
 AUTHOR: Sheila Demonteverde
 DATE: 05/28/2020
 Updated Dev Notes: 05/12/2026

 DESCRIPTION:
 This ViewModel handles business logic for
 managing task lists in TodoListFragment.

 FEATURES:
 - Retrieve all task lists
 - Add new task lists
 - Update task lists
 - Delete task lists
 - RxJava Single integration
 - Connects UI to repository layer

 NOTES:
 - Uses MVVM architecture
 - Uses LocalRepositoryImpl for database access
 - Supports asynchronous operations
=====================================================
*/
package com.sheilademonteverde.orgme.viewModels

import androidx.lifecycle.ViewModel
import com.sheilademonteverde.orgme.dataModels.ListModel
import com.sheilademonteverde.orgme.repositories.LocalRepositoryImpl
import io.reactivex.Single

/*-- LIST FRAGMENT VIEWMODEL --*/
class ListFragmentViewModel : ViewModel() 
{

    /*-- REPOSITORY INSTANCE: Access local database repository --*/
    private val localRepository = LocalRepositoryImpl.get()

    /*-- GET ALL TASK LISTS: Retrieves all task lists stored in the database. --*/
    fun getAllLists() = localRepository.getAllLists()

    /*-- DELETE TASK LIST: Deletes a task list using its ID. --*/
    fun deleteList(listId: Int) = localRepository.deleteList(listId)

    /*-- ADD NEW TASK LIST: Creates and saves a new task list. Uses RxJava Single for asynchronous execution --*/
    fun addList(name: String): Single<Long>? 
    {
        return Single.fromCallable 
        {
            val model = ListModel(name) // Create new task list model
            localRepository.addList(model) // Save list to database
        }
    }

    /*-- UPDATE TASK LIST: Updates task list name using list ID. --*/
    fun updateList(name: String, listId: Int) = localRepository.updateList(name, listId)

}
