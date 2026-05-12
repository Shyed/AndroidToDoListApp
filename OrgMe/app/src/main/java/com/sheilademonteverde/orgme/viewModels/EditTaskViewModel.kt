/*
=====================================================
 PROJECT: Android To Do List App (OrgMe)
 MODULE: viewModels/EditTaskViewModel
 AUTHOR: Sheila Demonteverde
 DATE: 05/28/2020
 Updated Dev Notes: 05/12/2026

 DESCRIPTION:
 This ViewModel handles business logic for
 editing existing task items.

 FEATURES:
 - Update task item information
 - Retrieve task item by ID
 - Connect UI to repository layer
 - Supports MVVM architecture

 NOTES:
 - Uses LocalRepositoryImpl for database access
 - Preserves UI-related data during configuration changes
=====================================================
*/

package com.sheilademonteverde.orgme.viewModels

import androidx.lifecycle.ViewModel
import com.sheilademonteverde.orgme.repositories.LocalRepositoryImpl

/*-- EDIT TASK VIEWMODEL --*/
class EditTaskViewModel : ViewModel() 
{
    /*-- REPOSITORY INSTANCE: Access local database repository --*/
    private val localRepository = LocalRepositoryImpl.get()

    /*-- UPDATE TASK ITEM: Updates task item information in the database. --*/
    fun updateListItem(name: String, dueDate: Long, listItemId: Int) =
        localRepository.updateListItem(name, dueDate, listItemId)

    /*-- GET TASK ITEM BY ID: Retrieves task item details using item ID. --*/
    fun getListItemByItemId(listItemId: Int) = localRepository.getListItemByItemId(listItemId)

}
