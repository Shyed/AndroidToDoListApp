/*
=====================================================
 PROJECT: Android To Do List App (OrgMe)
 MODULE: ui/TodoListAdapter
 AUTHOR: Sheila Demonteverde
 DATE: 05/28/2020
 Updated Dev Notes: 05/12/2026

 DESCRIPTION:
 This RecyclerView adapter displays all
 to-do task lists within the application.

 FEATURES:
 - RecyclerView adapter implementation
 - Dynamic list binding
 - Task list click handling
 - Edit list functionality
 - Delete list functionality
 - Spinner option handling

 NOTES:
 - Uses RecyclerView for efficient rendering
 - Connects list actions to fragment callbacks
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
import com.sheilademonteverde.orgme.dataModels.ListModel

/*-- TODO LIST ADAPTER: RecyclerView adapter that displays all available task lists. --*/
class TodoListAdapter(
    // Collection of task lists
    private val lists: List<ListModel>,
    // Click listener interface
    val clickListener: TodoListClickListener
) : RecyclerView.Adapter<TodoListViewHolder>() 
{

    /*-- CLICK LISTENER INTERFACE --*/
    interface TodoListClickListener 
    {
        fun listItemClicked(listModel: ListModel) // Open selected task list
        fun editClicked(listModel: ListModel)  // Edit selected task list
        fun deleteClicked(listModel: ListModel) // Delete selected task list
    }

    /*-- CONTEXT VARIABLE --*/
    private lateinit var context: Context

    /*-- CREATE VIEW HOLDER: Creates and returns a new ViewHolder --*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListViewHolder 
    {
        // Inflate RecyclerView row layout
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.todo_list_view_holder, parent, false)
        context = parent.context // Store parent context
        return TodoListViewHolder(view)
    }

    /*-- RETURN ITEM COUNT: Returns total number of task lists --*/
    override fun getItemCount(): Int 
    {
        return lists.size
    }

    /*-- BIND DATA TO VIEW HOLDER --*/
    override fun onBindViewHolder(holder: TodoListViewHolder, position: Int) {
        
        /*-- DISPLAY TASK LIST POSITION --*/
        holder.listPositionTextView.text = (position + 1).toString()
        
        /*-- DISPLAY TASK LIST TITLE --*/
        holder.listTitleTextView.text = lists[position].name
        
        /*-- TASK LIST CLICK ACTION --*/
        holder.itemView.setOnClickListener {
            clickListener.listItemClicked(lists[position]) // Open selected task list
        }

        /*-- SPINNER INITIALIZATION --*/
        holder.optionsSpinner.tag = 0

        /*-- SPINNER ITEM SELECTION LISTENER --*/
        holder.optionsSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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
                // Prevent automatic trigger during initial spinner setup
                val selections = parent!!.tag as Int
                if (selections > 0) {
                    val options = context.resources.getStringArray(R.array.spinner_options).toList() // Retrieve spinner menu options
                    
                    /*-- EDIT OPTION --*/
                    if (options[pos].equals(context.getString(R.string.edit), true)) 
                    {
                        clickListener.editClicked(lists[position])
                    } 
                    else 
                    {
                        /*-- DELETE OPTION --*/
                        clickListener.deleteClicked(lists[position])
                    }
                }
                // Mark spinner as initialized
                parent.tag = 1
            }

        }

    }
}
