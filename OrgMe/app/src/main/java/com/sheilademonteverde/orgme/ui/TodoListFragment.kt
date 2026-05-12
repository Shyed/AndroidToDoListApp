/*
=====================================================
 PROJECT: Android To Do List App (OrgMe)
 MODULE: ui/TodoListFragment
 AUTHOR: Sheila Demonteverde
 DATE: 05/28/2020
 Updated Dev Notes: 05/12/2026

 DESCRIPTION:
 This fragment displays all to-do lists
 within the application and manages
 task list interactions.

 FEATURES:
 - RecyclerView task list display
 - Create new task lists
 - Edit task lists
 - Delete task lists
 - Fragment navigation
 - LiveData observation
 - FloatingActionButton support
 - RxJava integration

 NOTES:
 - Uses MVVM architecture
 - Uses RecyclerView with custom adapter
 - Navigates to TaskDetailFragment
=====================================================
*/

package com.sheilademonteverde.orgme.ui

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sheilademonteverde.orgme.R
import com.sheilademonteverde.orgme.dataModels.ListModel
import com.sheilademonteverde.orgme.ui.raw.TaskList
import com.sheilademonteverde.orgme.viewModels.ListFragmentViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

/*-- TODO LIST FRAGMENT --*/
class TodoListFragment : Fragment(), TodoListAdapter.TodoListClickListener 
{

    /*-- RECYCLERVIEW --*/
    private lateinit var todoListRecyclerView: RecyclerView

    /*-- VIEWMODEL INITIALIZATION --*/
    private val viewModel: ListFragmentViewModel by lazy 
    {
        ViewModelProviders.of(this).get(ListFragmentViewModel::class.java)
    }

    /*-- CREATE FRAGMENT VIEW --*/
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? 
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todo_list, container, false)
    }

    /*-- VIEW CREATED EVENT --*/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) 
    {
        super.onViewCreated(view, savedInstanceState)

        /*-- RECYCLERVIEW SETUP --*/
        todoListRecyclerView = view.findViewById(R.id.lists_recyclerview)
        todoListRecyclerView.layoutManager = LinearLayoutManager(activity)

        /*-- OBSERVE TASK LISTS --*/
        viewModel.getAllLists().observe(viewLifecycleOwner, Observer 
         {
            it?.let {
                todoListRecyclerView.adapter = TodoListAdapter(it, this) // Attach adapter to RecyclerView
            }
        })

        /*-- FLOATING ACTION BUTTON --*/
        val fab = view.findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener 
        { _ ->
            showCreateTodoListDialog() // Open create task list dialog
        }
    }

    /*-- FRAGMENT INTERACTION INTERFACE --*/
    interface OnFragmentInteractionListener 
    {
        fun onTodoListClicked(list: TaskList)
    }

    /*-- FRAGMENT FACTORY METHOD --*/
    companion object {
        fun newInstance(): TodoListFragment 
        {
            return TodoListFragment()
        }
    }

    /*-- TASK LIST CLICK EVENT --*/
    override fun listItemClicked(listModel: ListModel) 
    {
        showTaskListItems(listModel.id) // Open selected task list
    }

    /*-- EDIT TASK LIST EVENT --*/
    override fun editClicked(listModel: ListModel) 
    {
        showEditDialog(listModel) // Open edit dialog
    }

    /*-- DELETE TASK LIST EVENT --*/
    override fun deleteClicked(listModel: ListModel) 
    {
        val builder = AlertDialog.Builder(requireActivity()) // Create confirmation dialog
        
        builder.setMessage(getString(R.string.delete_list_message))

        /*-- CONFIRM DELETE --*/
        builder.setPositiveButton(getString(R.string.yes)) 
        { dialogInterface, i ->
            dialogInterface.dismiss()
            viewModel.deleteList(listModel.id) // Delete selected list

            // Display success message
            Toast.makeText(
                requireActivity(),
                getString(R.string.delete_list_successful_message),
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

    /*-- CREATE NEW TODO LIST DIALOG --*/
    private fun showCreateTodoListDialog() 
    {
        activity?.let 
        {
            val dialogTitle = getString(R.string.name_of_list)
            val positiveButtonTitle = getString(R.string.create_list)
            val myDialog = AlertDialog.Builder(it)
            val todoTitleEditText = EditText(it)

            // Configure text input type
            todoTitleEditText.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_WORDS

            myDialog.setTitle(dialogTitle)
            myDialog.setView(todoTitleEditText)

            /*-- CREATE LIST ACTION --*/
            myDialog.setPositiveButton(positiveButtonTitle) 
            { dialog, _ ->
                val name = todoTitleEditText.text.toString()
                if (name.isNotEmpty()) 
                {
                    // Add new task list
                    viewModel.addList(name)?.subscribeOn(Schedulers.io())
                        ?.observeOn(AndroidSchedulers.mainThread())?.subscribeWith(
                            object : DisposableSingleObserver<Long>() 
                            {
                                override fun onSuccess(id: Long) 
                                {
                                    dialog.dismiss()
                                    showTaskListItems(id.toInt()) // Open newly created list
                                }

                                override fun onError(e: Throwable) 
                                {
                                    // Display error message
                                    Toast.makeText(
                                        requireActivity(),
                                        "Exception: ${e.localizedMessage}",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }

                            }
                        )
                }// if ends here
            }
            myDialog.create().show()
        }
    }

    /*-- EDIT TASK LIST DIALOG --*/
    private fun showEditDialog(listModel: ListModel) 
    {
        activity?.let 
        {
            val dialogTitle = getString(R.string.edit_list_text)
            val positiveButtonTitle = getString(R.string.save)
            val myDialog = AlertDialog.Builder(it)
            val todoTitleEditText = EditText(it)

            // Configure text input type
            todoTitleEditText.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_WORDS

            todoTitleEditText.setText(listModel.name.trim()) // Pre-fill current list name
            todoTitleEditText.setSelection(listModel.name.trim().length)

            myDialog.setTitle(dialogTitle)
            myDialog.setView(todoTitleEditText)

            /*-- SAVE UPDATED LIST --*/
            myDialog.setPositiveButton(positiveButtonTitle) 
            { dialog, _ ->
                val name = todoTitleEditText.text.toString()
                if (name.isNotEmpty()) 
                {
                    viewModel.updateList(name,listModel.id) // Update list name
                    dialog.dismiss()

                    // Display success message
                    Toast.makeText(
                        requireActivity(),
                        getString(R.string.list_updated_successfully),
                        Toast.LENGTH_LONG
                    ).show()
                }// if ends here
            }
            myDialog.create().show()
        }
    }

    /*-- NAVIGATE TO TASK DETAIL SCREEN --*/
    private fun showTaskListItems(listId: Int) 
    {
        view?.let 
        {
            // Create navigation actio
            val action =
                TodoListFragmentDirections.actionTodoListFragmentToTaskDetailFragment(
                    listId
                )
            // Navigate to task detail fragment
            it.findNavController().navigate(action)
        }
    }
}
