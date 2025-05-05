/*
Project Name: OrgMe
Author: Sheila Demonteverde
Module Name: TaskList
Purpose: Object that contains a sublist of tasks items
Date: 05/28/2020
*/

package com.sheilademonteverde.orgme.ui.raw

import android.os.Parcel
import android.os.Parcelable

//The TaskList class takes a name then an array of tasks (strings)
class TaskList(val name: String, val tasks: ArrayList<String> = ArrayList()) : Parcelable {

    //
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.createStringArrayList()!!
    )

    //
    companion object CREATOR: Parcelable.Creator<TaskList> {
        override fun createFromParcel(source: Parcel): TaskList =
            TaskList(source)

        override fun newArray(size: Int): Array<TaskList?> = arrayOfNulls(size)
    }

    //
    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeStringList(tasks)
    }
    //
    override fun describeContents() = 0
}