/*
Project Name: OrgMe
Author: Sheila Demonteverde
Module Name: TaskListAdapter
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
import com.sheilademonteverde.orgme.dataModels.ListItemModel

class TaskListAdapter(
    var list: List<ListItemModel>,
    val clickListener: TaskClickListener
) : RecyclerView.Adapter<TaskListViewHolder>() {

    interface TaskClickListener {
        fun editClicked(listItem: ListItemModel)
        fun deleteClicked(listItem: ListItemModel)
    }

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.task_view_holder, parent, false)
        context = parent.context
        return TaskListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TaskListViewHolder, position: Int) {
        holder.taskIdTextView.text = (position + 1).toString()
        holder.taskTextView.text = list[position].name
        holder.dateTextView.text = Utils.getReadableDate(list[position].dueDate)

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
                        clickListener.editClicked(list[position])
                    } else {
                        clickListener.deleteClicked(list[position])
                    }
                }
                parent.tag = 1
            }

        }

    }
}