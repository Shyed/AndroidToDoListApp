/*
Project Name: OrgMe
Author: Sheila Demonteverde
Module Name: TodoListFragment
Date: 05/28/2020
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

class TodoListFragment : Fragment(), TodoListAdapter.TodoListClickListener {

    private lateinit var todoListRecyclerView: RecyclerView

    private val viewModel: ListFragmentViewModel by lazy {
        ViewModelProviders.of(this).get(ListFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todo_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        todoListRecyclerView = view.findViewById(R.id.lists_recyclerview)
        todoListRecyclerView.layoutManager = LinearLayoutManager(activity)

        viewModel.getAllLists().observe(viewLifecycleOwner, Observer {
            it?.let {
                todoListRecyclerView.adapter = TodoListAdapter(it, this)
            }
        })

        val fab = view.findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { _ ->
            showCreateTodoListDialog()
        }
    }

    interface OnFragmentInteractionListener {
        fun onTodoListClicked(list: TaskList)
    }

    companion object {
        fun newInstance(): TodoListFragment {
            return TodoListFragment()
        }
    }

    override fun listItemClicked(listModel: ListModel) {
        showTaskListItems(listModel.id)
    }

    override fun editClicked(listModel: ListModel) {
        showEditDialog(listModel)
    }

    override fun deleteClicked(listModel: ListModel) {
        val builder = AlertDialog.Builder(requireActivity())
        builder.setMessage(getString(R.string.delete_list_message))
        builder.setPositiveButton(getString(R.string.yes)) { dialogInterface, i ->
            dialogInterface.dismiss()
            viewModel.deleteList(listModel.id)
            Toast.makeText(
                requireActivity(),
                getString(R.string.delete_list_successful_message),
                Toast.LENGTH_SHORT
            ).show()
        }
        builder.setNegativeButton(getString(R.string.no)) { dialogInterface, i ->
            dialogInterface.dismiss()
        }

        val alert = builder.create()
        alert.show()
    }

    private fun showCreateTodoListDialog() {
        activity?.let {
            val dialogTitle = getString(R.string.name_of_list)
            val positiveButtonTitle = getString(R.string.create_list)
            val myDialog = AlertDialog.Builder(it)
            val todoTitleEditText = EditText(it)
            todoTitleEditText.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_WORDS

            myDialog.setTitle(dialogTitle)
            myDialog.setView(todoTitleEditText)

            myDialog.setPositiveButton(positiveButtonTitle) { dialog, _ ->
                val name = todoTitleEditText.text.toString()
                if (name.isNotEmpty()) {
                    viewModel.addList(name)?.subscribeOn(Schedulers.io())
                        ?.observeOn(AndroidSchedulers.mainThread())?.subscribeWith(
                            object : DisposableSingleObserver<Long>() {
                                override fun onSuccess(id: Long) {
                                    dialog.dismiss()
                                    showTaskListItems(id.toInt())
                                }

                                override fun onError(e: Throwable) {
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

    private fun showEditDialog(listModel: ListModel) {
        activity?.let {
            val dialogTitle = getString(R.string.edit_list_text)
            val positiveButtonTitle = getString(R.string.save)
            val myDialog = AlertDialog.Builder(it)
            val todoTitleEditText = EditText(it)
            todoTitleEditText.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_WORDS

            todoTitleEditText.setText(listModel.name.trim())
            todoTitleEditText.setSelection(listModel.name.trim().length)

            myDialog.setTitle(dialogTitle)
            myDialog.setView(todoTitleEditText)

            myDialog.setPositiveButton(positiveButtonTitle) { dialog, _ ->
                val name = todoTitleEditText.text.toString()
                if (name.isNotEmpty()) {
                    viewModel.updateList(name,listModel.id)
                    dialog.dismiss()
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

    private fun showTaskListItems(listId: Int) {
        view?.let {
            val action =
                TodoListFragmentDirections.actionTodoListFragmentToTaskDetailFragment(
                    listId
                )
            it.findNavController().navigate(action)
        }
    }
}
