/*
Project Name: OrgMe
Author: Sheila Demonteverde
Module Name: DetailFragmentViewModel
Date: 05/28/2020
*/

package com.sheilademonteverde.orgme.viewModels

import androidx.lifecycle.ViewModel
import com.sheilademonteverde.orgme.repositories.LocalRepositoryImpl

class DetailFragmentViewModel : ViewModel() {

    private val localRepository = LocalRepositoryImpl.get()

    fun getAllItemsOfList(listId: Int) = localRepository.getAllItemsOfList(listId)

    fun getListById(listId: Int) = localRepository.getListById(listId)

    fun deleteListItem(listItemId: Int) = localRepository.deleteListItem(listItemId)

}