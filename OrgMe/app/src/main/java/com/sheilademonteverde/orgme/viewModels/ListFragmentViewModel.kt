/*
Project Name: OrgMe
Author: Sheila Demonteverde
Module Name: ListFragmentViewModel
Date: 05/28/2020
*/


package com.sheilademonteverde.orgme.viewModels

import androidx.lifecycle.ViewModel
import com.sheilademonteverde.orgme.dataModels.ListModel
import com.sheilademonteverde.orgme.repositories.LocalRepositoryImpl
import io.reactivex.Single

class ListFragmentViewModel : ViewModel() {

    private val localRepository = LocalRepositoryImpl.get()

    fun getAllLists() = localRepository.getAllLists()

    fun deleteList(listId: Int) = localRepository.deleteList(listId)

    fun addList(name: String): Single<Long>? {
        return Single.fromCallable {
            val model = ListModel(name)
            localRepository.addList(model)
        }
    }

    fun updateList(name: String, listId: Int) = localRepository.updateList(name, listId)

}