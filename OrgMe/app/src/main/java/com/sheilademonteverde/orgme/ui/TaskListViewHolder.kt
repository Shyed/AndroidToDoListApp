/*
=====================================================
 PROJECT: Android To Do List App (OrgMe)
 MODULE: ui/TaskListViewHolder
 AUTHOR: Sheila Demonteverde
 DATE: 05/28/2020
 Updated Dev Notes: 05/12/2026

 DESCRIPTION:
 This ViewHolder class stores references
 to UI components used in each RecyclerView
 task item layout.

 FEATURES:
 - Holds task title view
 - Holds task date view
 - Holds task ID view
 - Holds options spinner
 - Improves RecyclerView performance

 NOTES:
 - Used with TaskListAdapter
 - Reduces repeated findViewById calls
=====================================================
*/


package com.sheilademonteverde.orgme.ui

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sheilademonteverde.orgme.R

/*-- TASK LIST VIEW HOLDER --*/
class TaskListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) 
{
    /*-- TASK TITLE TEXTVIEW: Displays task name/title --*/
    val taskTextView = itemView.findViewById<TextView>(R.id.taskTextView) as TextView

    /*-- TASK DATE TEXTVIEW: Displays formatted due date --*/
    val dateTextView = itemView.findViewById<TextView>(R.id.dateTextView) as TextView
    
    /*-- TASK ID TEXTVIEW: Displays task item number --*/
    val taskIdTextView = itemView.findViewById<TextView>(R.id.taskIdTextView) as TextView
    
    /*-- OPTIONS SPINNER: Spinner containing edit/delete options --*/
    var optionsSpinner = itemView.findViewById<MyCustomSpinner>(R.id.options_spinner)
}
