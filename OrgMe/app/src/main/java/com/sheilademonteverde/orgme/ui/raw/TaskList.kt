/*
=====================================================
 PROJECT: Android To Do List App (OrgMe)
 MODULE: ui/raw/TaskList
 AUTHOR: Sheila Demonteverde
 PURPOSE: Object that contains a sublist of task items
 DATE: 05/28/2020
 Updated Dev Notes: 05/12/2026

 DESCRIPTION:
 This class represents a task list object
 containing:
 - a task list name
 - a collection of task items

 FEATURES:
 - Parcelable implementation
 - Stores task list name
 - Stores multiple task items
 - Supports Android object passing between activities

 NOTES:
 - Implements Parcelable for efficient data transfer
 - Used in earlier raw-data implementation
=====================================================
*/

package com.sheilademonteverde.orgme.ui.raw

import android.os.Parcel
import android.os.Parcelable

/*-- TASK LIST CLASS --*/
class TaskList(val name: String, val tasks: ArrayList<String> = ArrayList()) : Parcelable {

    /*-- PARCEL CONSTRUCTOR --*/
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.createStringArrayList()!!
    ) // Reconstruct object from Parcel data

    /*-- PARCELABLE CREATOR --*/
    companion object CREATOR: Parcelable.Creator<TaskList>  
    {
        // Create object from Parcel
        override fun createFromParcel(source: Parcel): TaskList =
            TaskList(source)

        override fun newArray(size: Int): Array<TaskList?> = arrayOfNulls(size) // Create Parcelable array
    }

    /*-- WRITE OBJECT TO PARCEL --*/
    override fun writeToParcel(dest: Parcel, flags: Int) 
    {
        dest.writeString(name) // Write task list name
        dest.writeStringList(tasks) // Write task items
    }
    
    /*-- DESCRIBE PARCEL CONTENTS --*/
    override fun describeContents() = 0
}
