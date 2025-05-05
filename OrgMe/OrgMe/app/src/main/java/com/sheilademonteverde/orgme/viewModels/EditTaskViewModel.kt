/*
Project Name: OrgMe
Author: Sheila Demonteverde
Module Name: EditTaskViewModel
Date: 05/28/2020
*/

package com.sheilademonteverde.orgme.viewModels

import androidx.lifecycle.ViewModel
import com.sheilademonteverde.orgme.repositories.LocalRepositoryImpl

class EditTaskViewModel : ViewModel() {

    private val localRepository = LocalRepositoryImpl.get()

    fun updateListItem(name: String, dueDate: Long, listItemId: Int) =
        localRepository.updateListItem(name, dueDate, listItemId)

    fun getListItemByItemId(listItemId: Int) = localRepository.getListItemByItemId(listItemId)

}