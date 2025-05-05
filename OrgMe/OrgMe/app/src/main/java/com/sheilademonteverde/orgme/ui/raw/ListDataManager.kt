/*
Project Name: OrgMe
Author: Sheila Demonteverde
Module Name: ListDataManager
Purpose: Objects that Reads and Writes Data
Date: 05/28/2020
*/

package com.sheilademonteverde.orgme.ui.raw

import android.app.Application
import androidx.preference.PreferenceManager
import androidx.lifecycle.AndroidViewModel

class ListDataManager(application: Application) : AndroidViewModel(application) {

    private val context = application.applicationContext
    //saving method: saves data
    fun saveList(list: TaskList) {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context).edit()
        //The Preference Manager does not save array lists so we must convert it to a Set
        sharedPrefs.putStringSet(list.name, list.tasks.toHashSet())
        sharedPrefs.apply()
    }

    //reading method: reads data and returns an array list of tasks list
    fun readLists(): ArrayList<TaskList> {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)
        //get contents
        val contents = sharedPrefs.all
        //create a list to hold our tasks list
        val taskLists = ArrayList<TaskList>()

        // loop through the keys (lookup)
        for (taskList in contents) {
            //convert saved hash set into an array list
            val taskItems = ArrayList(taskList.value as HashSet<String>)
            //create a task list from converted hash set
            val list =
                TaskList(
                    taskList.key,
                    taskItems
                )
            //Add to the array
            taskLists.add(list)
        }
        return taskLists
    }
}