/*
=====================================================
 PROJECT: Android To Do List App (OrgMe)
 MODULE: ui/TodoListViewHolder
 AUTHOR: Sheila Demonteverde
 DATE: 05/28/2020
 Updated Dev Notes: 05/12/2026

 DESCRIPTION:
 This ViewHolder class stores references
 to UI components used in each RecyclerView
 to-do list item layout.

 FEATURES:
 - Holds task list position view
 - Holds task list title view
 - Holds options spinner
 - Improves RecyclerView performance

 NOTES:
 - Used with TodoListAdapter
 - Reduces repeated findViewById calls
=====================================================
*/

package com.sheilademonteverde.orgme.ui

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sheilademonteverde.orgme.R

/*-- TODO LIST VIEW HOLDER: ViewHolder for RecyclerView rows displaying task list information --*/
class TodoListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) 
{
    /*-- TASK LIST POSITION TEXTVIEW: Displays task list number --*/
    var listPositionTextView = itemView.findViewById<TextView>(R.id.itemNumber)

    /*-- TASK LIST TITLE TEXTVIEW: Displays task list title/name --*/
    var listTitleTextView = itemView.findViewById<TextView>(R.id.itemString)
    
    /*-- OPTIONS SPINNER: Spinner containing edit/delete options --*/
    var optionsSpinner = itemView.findViewById<MyCustomSpinner>(R.id.options_spinner)
}
