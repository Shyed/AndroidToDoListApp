/*
=====================================================
 PROJECT: Android To Do List App (OrgMe)
 MODULE: ui/TaskListAdapter
 AUTHOR: Sheila Demonteverde
 DATE: 05/28/2020
 Updated Dev Notes: 05/12/2026

 DESCRIPTION:
 This RecyclerView adapter displays task items
 within the task detail screen.

 FEATURES:
 - RecyclerView adapter implementation
 - Dynamic task item binding
 - Edit task functionality
 - Delete task functionality
 - Spinner option handling
 - Custom ViewHolder integration

 NOTES:
 - Uses RecyclerView for efficient list rendering
 - Connects task actions to fragment callbacks
=====================================================
*/


package com.sheilademonteverde.orgme.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.sheilademonteverde.orgme.R
import com.sheilademonteverde.orgme.dataModels.ListItemModel

/*-- TASK LIST ADAPTER --*/
class TaskListAdapter(
    // List of task items
    var list: List<ListItemModel>, 
    // Click event listener
    val clickListener: TaskClickListener
) : RecyclerView.Adapter<TaskListViewHolder>() 
{
     /*-- TASK CLICK INTERFACE --*/
    interface TaskClickListener 
    {
        fun editClicked(listItem: ListItemModel) // Edit task action
        fun deleteClicked(listItem: ListItemModel) // Delete task action
    }

    /*-- CONTEXT VARIABLE --*/
    private lateinit var context: Context

     /*-- CREATE VIEW HOLDER --*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskListViewHolder 
    {
        // Inflate RecyclerView item layout
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.task_view_holder, parent, false)
        context = parent.context // Store context reference
        return TaskListViewHolder(view)
    }

    /*-- RETURN ITEM COUNT --*/
    override fun getItemCount(): Int 
    {
        return list.size
    }

    /*-- BIND VIEW HOLDER DATA --*/
    override fun onBindViewHolder(holder: TaskListViewHolder, position: Int) 
    {
        holder.taskIdTextView.text = (position + 1).toString() // Display task number
        holder.taskTextView.text = list[position].name // Display task name
        holder.dateTextView.text = Utils.getReadableDate(list[position].dueDate) // Display formatted due date

        /*-- SPINNER INITIALIZATION --*/
        holder.optionsSpinner.tag = 0

        /*-- SPINNER ITEM SELECTION LISTENER --*/
        holder.optionsSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener 
        {
            override fun onNothingSelected(parent: AdapterView<*>?) 
            {
                // No action required
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                pos: Int,
                id: Long
            ) 
            {
                // Prevent automatic initial selection trigger    
                val selections = parent!!.tag as Int
                if (selections > 0) 
                {
                    val options = context.resources.getStringArray(R.array.spinner_options).toList() // Retrieve spinner options

                    /*-- EDIT TASK OPTION --*/
                    if (options[pos].equals(context.getString(R.string.edit), true)) 
                    {
                        clickListener.editClicked(list[position])
                    } 
                    else 
                    {
                        /*-- DELETE TASK OPTION --*/
                        clickListener.deleteClicked(list[position])
                    }
                }
                // Mark spinner interaction completed
                parent.tag = 1
            }

        }

    }
}
