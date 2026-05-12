/*
=====================================================
 PROJECT: Android To Do List App (OrgMe)
 MODULE: viewModels/DetailFragmentViewModel
 AUTHOR: Sheila Demonteverde
 DATE: 05/28/2020
 Updated Dev Notes: 05/12/2026

 DESCRIPTION:
 This ViewModel handles business logic for
 the TaskDetailFragment screen.

 FEATURES:
 - Retrieve task items by list ID
 - Retrieve task list information
 - Delete task items
 - Connect UI to repository layer

 NOTES:
 - Uses MVVM architecture
 - Accesses Room database through repository
 - Preserves UI-related data during configuration changes
=====================================================
*/

package com.sheilademonteverde.orgme.viewModels

import androidx.lifecycle.ViewModel
import com.sheilademonteverde.orgme.repositories.LocalRepositoryImpl

/*-- DETAIL FRAGMENT VIEWMODEL --*/
class DetailFragmentViewModel : ViewModel() {

    /*-- REPOSITORY INSTANCE: Access local database repository --*/
    private val localRepository = LocalRepositoryImpl.get()

    /*-- GET TASK ITEMS OF LIST: Retrieves all task items belonging to a selected list --*/
    fun getAllItemsOfList(listId: Int) = localRepository.getAllItemsOfList(listId)

    /*-- GET TASK LIST BY ID: Retrieves task list details using list ID. --*/
    fun getListById(listId: Int) = localRepository.getListById(listId)

    /*-- DELETE TASK ITEM: Deletes a selected task item from the database. --*/
    fun deleteListItem(listItemId: Int) = localRepository.deleteListItem(listItemId)

}
