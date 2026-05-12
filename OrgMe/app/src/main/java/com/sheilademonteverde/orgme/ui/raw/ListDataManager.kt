/*
=====================================================
 PROJECT: Android To Do List App (OrgMe)
 MODULE: ui/raw/ListDataManager
 AUTHOR: Sheila Demonteverde
 PURPOSE: Objects that Reads and Writes Data
 DATE: 05/28/2020
 Updated Dev Notes: 05/12/2026

 DESCRIPTION:
 This ViewModel class manages saving and
 retrieving task list data using SharedPreferences.

 FEATURES:
 - Save task lists locally
 - Read saved task lists
 - SharedPreferences integration
 - HashSet to ArrayList conversion
 - AndroidViewModel implementation

 NOTES:
 - SharedPreferences stores data as key-value pairs
 - Used before Room database implementation
=====================================================
*/

package com.sheilademonteverde.orgme.ui.raw

import android.app.Application
import androidx.preference.PreferenceManager
import androidx.lifecycle.AndroidViewModel

/*-- LIST DATA MANAGER CLASS --*/
class ListDataManager(application: Application) : AndroidViewModel(application) 
{
    /*-- APPLICATION CONTEXT --*/
    private val context = application.applicationContext

    /*-- SAVE TASK LIST: Saves task list data into SharedPreferences --*/
    fun saveList(list: TaskList) 
    {
        // Retrieve SharedPreferences editor
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context).edit()
        
        //The Preference Manager does not save array lists so we must convert it to a Set
        sharedPrefs.putStringSet(list.name, list.tasks.toHashSet())
        sharedPrefs.apply() // Save changes asynchronously
    }

    /*-- READ SAVED TASK LISTS: Reads and returns all saved task lists --*/
    fun readLists(): ArrayList<TaskList> 
    {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context) // Access SharedPreferences
        val contents = sharedPrefs.all // Retrieve all saved data
        val taskLists = ArrayList<TaskList>() // Store reconstructed task lists

         /*-- LOOP THROUGH SAVED DATA --*/
        for (taskList in contents) 
        {
            val taskItems = ArrayList(taskList.value as HashSet<String>) // Convert saved HashSet into ArrayList

            // Create TaskList object
            val list =
                TaskList(
                    taskList.key,
                    taskItems
                )
            
            taskLists.add(list) // Add task list to final collection
        }
        return taskLists
    }
}
