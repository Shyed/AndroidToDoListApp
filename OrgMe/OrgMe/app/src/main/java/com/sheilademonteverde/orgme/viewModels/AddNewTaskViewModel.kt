/*
Project Name: OrgMe
Author: Sheila Demonteverde
Module Name: AddNewTaskViewModel
Date: 05/28/2020
*/

package com.sheilademonteverde.orgme.viewModels

import androidx.lifecycle.ViewModel
import com.sheilademonteverde.orgme.repositories.LocalRepositoryImpl

class AddNewTaskViewModel : ViewModel() {

    private val localRepository = LocalRepositoryImpl.get()

    fun addListItem(name: String, dueDate: Long, itemListId: Int) =
        localRepository.addListItem(name, dueDate, itemListId)


}