/*
Project Name: OrgMe
Author: Sheila Demonteverde
Module Name: TodoListAdapter
Date: 05/28/2020
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

//Object with constructor that accepts array list
class TodoListAdapter(
    private val lists: List<ListModel>,
    val clickListener: TodoListClickListener
) : RecyclerView.Adapter<TodoListViewHolder>() {

    interface TodoListClickListener {
        fun listItemClicked(listModel: ListModel)
        fun editClicked(listModel: ListModel)
        fun deleteClicked(listModel: ListModel)
    }

    private lateinit var context: Context

    //Returns a new view holder.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.todo_list_view_holder, parent, false)
        context = parent.context
        return TodoListViewHolder(view)
    }

    //Tells the recycler view how many items are in our list
    override fun getItemCount(): Int {
        return lists.size
    }

    //Customize each row according to our data. Passing in a new recycler view and receives current position in the list.
    override fun onBindViewHolder(holder: TodoListViewHolder, position: Int) {
        //add one item to the list
        holder.listPositionTextView.text = (position + 1).toString()
        //set the name of the to-do item
        holder.listTitleTextView.text = lists[position].name

        holder.itemView.setOnClickListener {
            clickListener.listItemClicked(lists[position])
        }

        holder.optionsSpinner.tag = 0
        holder.optionsSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                pos: Int,
                id: Long
            ) {
                val selections = parent!!.tag as Int
                if (selections > 0) {
                    val options = context.resources.getStringArray(R.array.spinner_options).toList()
                    if (options[pos].equals(context.getString(R.string.edit), true)) {
                        clickListener.editClicked(lists[position])
                    } else {
                        clickListener.deleteClicked(lists[position])
                    }
                }
                parent.tag = 1
            }

        }

    }
}