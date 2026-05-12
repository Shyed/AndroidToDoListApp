/*
=====================================================
 PROJECT: Android To Do List App (OrgMe)
 MODULE: viewModels/AddNewTaskViewModel
 AUTHOR: Sheila Demonteverde
 DATE: 05/28/2020
 Updated Dev Notes: 05/12/2026

 DESCRIPTION:
 This ViewModel handles business logic for
 adding new task items to a task list.

 FEATURES:
 - Connects UI to repository layer
 - Adds new task items
 - Supports MVVM architecture
 - Separates UI and data logic

 NOTES:
 - Uses LocalRepositoryImpl for database access
 - Survives configuration changes
=====================================================
*/

package com.sheilademonteverde.orgme.viewModels

import androidx.lifecycle.ViewModel
import com.sheilademonteverde.orgme.repositories.LocalRepositoryImpl

/*-- ADD NEW TASK VIEWMODEL --*/
class AddNewTaskViewModel : ViewModel() 
{

    /*-- REPOSITORY INSTANCE: Access local database repository --*/
    private val localRepository = LocalRepositoryImpl.get()

    /*-- ADD TASK ITEM: Sends new task item data to the repository layer. --*/
    fun addListItem(name: String, dueDate: Long, itemListId: Int) =
        localRepository.addListItem(name, dueDate, itemListId)
}
