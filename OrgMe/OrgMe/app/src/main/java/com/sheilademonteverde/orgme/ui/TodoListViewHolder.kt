/*
Project Name: OrgMe
Author: Sheila Demonteverde
Module Name: TodoListViewHolder
Date: 05/28/2020
*/
package com.sheilademonteverde.orgme.ui

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sheilademonteverde.orgme.R

//Primary constructor takes in a regular android view and passes it into its superclass constructor
class TodoListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    //Properties to change text within the view
    var listPositionTextView = itemView.findViewById<TextView>(R.id.itemNumber)
    var listTitleTextView = itemView.findViewById<TextView>(R.id.itemString)
    var optionsSpinner = itemView.findViewById<MyCustomSpinner>(R.id.options_spinner)
}