/*
=====================================================
 PROJECT: Android To Do List App (OrgMe)
 MODULE: ui/TaskDetailFragment
 AUTHOR: Sheila Demonteverde
 DATE: 05/28/2020
 Updated Dev Notes: 05/12/2026

 DESCRIPTION:
 This fragment displays all task items
 belonging to a selected task list.

 FEATURES:
 - RecyclerView task display
 - Add new task functionality
 - Edit existing tasks
 - Delete task items
 - LiveData observation
 - AlertDialog confirmations
 - Toolbar title updates

 NOTES:
 - Uses MVVM architecture
 - Uses RecyclerView with custom adapter
 - Supports task management operations
=====================================================
*/

package com.sheilademonteverde.orgme.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sheilademonteverde.orgme.R
import com.sheilademonteverde.orgme.dataModels.ListItemModel
import com.sheilademonteverde.orgme.ui.raw.TaskList
import com.sheilademonteverde.orgme.viewModels.DetailFragmentViewModel
import kotlinx.android.synthetic.main.activity_main.*

/*-- TASK DETAIL FRAGMENT --*/
class TaskDetailFragment : Fragment(), TaskListAdapter.TaskClickListener 
{
    /*-- RECYCLERVIEW --*/
    lateinit var taskListRecyclerView: RecyclerView

    /*-- TASK LIST VARIABLES --*/
    private var listId = 0

    /*-- VIEWMODEL INITIALIZATION --*/
    private val viewModel: DetailFragmentViewModel by lazy 
    {
        ViewModelProviders.of(this).get(DetailFragmentViewModel::class.java)
    }

    /*-- CREATE FRAGMENT VIEW --*/
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? 
    {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_task_detail, container, false)
        
        /*-- ADD TASK BUTTON --*/
        val addTaskBtn = view.findViewById<FloatingActionButton>(R.id.addTaskBtn)
        addTaskBtn.setOnClickListener 
        {
             // Open AddNewTaskToList activity
            val intentToSend = Intent(activity, AddNewTaskToList::class.java)
            intentToSend.putExtra("itemListId", listId) // Pass selected list ID
            startActivity(intentToSend)
        }
        return view
    }

    /*-- VIEW CREATED EVENT --*/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) 
    {
        super.onViewCreated(view, savedInstanceState)

        /*-- RETRIEVE FRAGMENT ARGUMENTS --*/
        arguments?.let 
        {
            val args = TaskDetailFragmentArgs.fromBundle(it)
            listId = args.listId
        }

        /*-- RECYCLERVIEW SETUP --*/
        taskListRecyclerView = view.findViewById(R.id.task_list_recyclerview)
        taskListRecyclerView.layoutManager = LinearLayoutManager(activity)

        /*-- OBSERVE TASK LIST TITLE --*/
        viewModel.getListById(listId).observe(viewLifecycleOwner, Observer 
                                              {
            if (it != null) {
                activity?.toolbar?.title = it.name // Update toolbar title
            }
        })

        /*-- OBSERVE TASK ITEMS --*/
        viewModel.getAllItemsOfList(listId).observe(viewLifecycleOwner, Observer 
         {
            it?.let 
            {
                taskListRecyclerView.adapter = TaskListAdapter(it, this) // Attach adapter to RecyclerView
            }
        })

    }

    /*-- FRAGMENT FACTORY METHOD --*/
    companion object 
    {
        private val ARG_LIST = "list"
        fun newInstance(list: TaskList): TaskDetailFragment {
            val bundle = Bundle()  // Create bundle for fragment arguments
            bundle.putParcelable(ARG_LIST, list)
            val fragment = TaskDetailFragment() // Create fragment instance
            fragment.arguments = bundle
            return fragment
        }
    }

    /*-- EDIT TASK ACTION --*/
    override fun editClicked(listItem: ListItemModel) 
    {
        val intentToSend = Intent(activity, EditTaskActivity::class.java)
        intentToSend.putExtra("listItemId", listItem.id) // Pass selected task item ID
        startActivity(intentToSend)
    }

    /*-- DELETE TASK ACTION --*/
    override fun deleteClicked(listItem: ListItemModel) 
    {
        val builder = AlertDialog.Builder(requireActivity()) // Create confirmation dialog
        builder.setMessage(getString(R.string.delete_list_item_message))

        /*-- CONFIRM DELETE --*/
        builder.setPositiveButton(getString(R.string.yes)) 
        { dialogInterface, i ->
            dialogInterface.dismiss()
            viewModel.deleteListItem(listItem.id) // Delete task item

            // Display success message
            Toast.makeText(
                requireActivity(),
                getString(R.string.list_item_deleted_successfully),
                Toast.LENGTH_SHORT
            ).show()
        }

        /*-- CANCEL DELETE --*/
        builder.setNegativeButton(getString(R.string.no)) 
        { dialogInterface, i ->
            dialogInterface.dismiss()
        }

        val alert = builder.create() // Display dialog
        alert.show()
    }

}
