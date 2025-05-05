/*
Project Name: OrgMe
Author: Sheila Demonteverde
Module Name: TaskDetailFragment
Date: 05/28/2020
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

class TaskDetailFragment : Fragment(), TaskListAdapter.TaskClickListener {

    lateinit var taskListRecyclerView: RecyclerView

    private var listId = 0

    private val viewModel: DetailFragmentViewModel by lazy {
        ViewModelProviders.of(this).get(DetailFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_task_detail, container, false)
        val addTaskBtn = view.findViewById<FloatingActionButton>(R.id.addTaskBtn)
        addTaskBtn.setOnClickListener {
            val intentToSend = Intent(activity, AddNewTaskToList::class.java)
            intentToSend.putExtra("itemListId", listId)
            startActivity(intentToSend)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val args = TaskDetailFragmentArgs.fromBundle(it)
            listId = args.listId
        }

        taskListRecyclerView = view.findViewById(R.id.task_list_recyclerview)
        taskListRecyclerView.layoutManager = LinearLayoutManager(activity)

        viewModel.getListById(listId).observe(viewLifecycleOwner, Observer {
            if (it != null) {
                activity?.toolbar?.title = it.name
            }
        })

        viewModel.getAllItemsOfList(listId).observe(viewLifecycleOwner, Observer {
            it?.let {
                taskListRecyclerView.adapter = TaskListAdapter(it, this)
            }
        })

    }

    companion object {
        private val ARG_LIST = "list"
        fun newInstance(list: TaskList): TaskDetailFragment {
            val bundle = Bundle()
            bundle.putParcelable(ARG_LIST, list)
            val fragment = TaskDetailFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun editClicked(listItem: ListItemModel) {
        val intentToSend = Intent(activity, EditTaskActivity::class.java)
        intentToSend.putExtra("listItemId", listItem.id)
        startActivity(intentToSend)
    }

    override fun deleteClicked(listItem: ListItemModel) {
        val builder = AlertDialog.Builder(requireActivity())
        builder.setMessage(getString(R.string.delete_list_item_message))
        builder.setPositiveButton(getString(R.string.yes)) { dialogInterface, i ->
            dialogInterface.dismiss()
            viewModel.deleteListItem(listItem.id)
            Toast.makeText(
                requireActivity(),
                getString(R.string.list_item_deleted_successfully),
                Toast.LENGTH_SHORT
            ).show()
        }
        builder.setNegativeButton(getString(R.string.no)) { dialogInterface, i ->
            dialogInterface.dismiss()
        }

        val alert = builder.create()
        alert.show()
    }

}
