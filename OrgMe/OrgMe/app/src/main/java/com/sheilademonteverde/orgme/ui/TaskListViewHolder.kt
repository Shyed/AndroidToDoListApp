/*
Project Name: OrgMe
Author: Sheila Demonteverde
Module Name: TaskListViewHolder
Date: 05/28/2020
*/


package com.sheilademonteverde.orgme.ui

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sheilademonteverde.orgme.R

class TaskListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    //Properties to change text within the view
    val taskTextView = itemView.findViewById<TextView>(R.id.taskTextView) as TextView
    val dateTextView = itemView.findViewById<TextView>(R.id.dateTextView) as TextView
    val taskIdTextView = itemView.findViewById<TextView>(R.id.taskIdTextView) as TextView
    var optionsSpinner = itemView.findViewById<MyCustomSpinner>(R.id.options_spinner)
}
