/*
=====================================================
 PROJECT: Android To Do List App (OrgMe)
 MODULE: ui/EditTaskActivity
 AUTHOR: Sheila Demonteverde
 DATE: 05/28/2020
 Updated Dev Notes: 05/12/2026

 DESCRIPTION:
 This activity allows users to edit existing
 task items within a selected task list.

 FEATURES:
 - Edit existing task details
 - Date and time selection
 - Task validation
 - LiveData observation
 - Toolbar navigation
 - Task update functionality

 NOTES:
 - Uses MVVM architecture
 - Retrieves task data using ViewModel
 - Updates Room database records
=====================================================
*/

package com.sheilademonteverde.orgme.ui

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sheilademonteverde.orgme.R
import com.sheilademonteverde.orgme.viewModels.EditTaskViewModel
import java.util.*

/*-- EDIT TASK ACTIVITY --*/
class EditTaskActivity : AppCompatActivity() 
{
    /*-- TASK VARIABLES --*/
    private var listItemId = 0

    /*-- UI COMPONENTS --*/
    private lateinit var taskEditText: EditText
    private lateinit var dateTextView: TextView
    private var selectedDate: Long? = null

    /*-- VIEWMODEL INITIALIZATION --*/
    private val viewModel: EditTaskViewModel by lazy 
    {
        ViewModelProviders.of(this).get(EditTaskViewModel::class.java)
    }

    /*-- ACTIVITY CREATION --*/
    override fun onCreate(savedInstanceState: Bundle?) 
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_task) // Load activity layout

        supportActionBar?.setDisplayHomeAsUpEnabled(true) // Enable toolbar back button
        supportActionBar?.title = getString(R.string.edit_task) // Set toolbar title

        taskEditText = findViewById(R.id.taskEditText) // Connect UI components
        dateTextView = findViewById(R.id.dateTextView)

        listItemId = intent.getIntExtra("listItemId", 0) // Retrieve selected task item ID

        /*-- OBSERVE TASK DATA --*/
        viewModel.getListItemByItemId(listItemId).observe(this, Observer 
        {
            it?.let
            {
                taskEditText.setText(it.name) // Display task name
                selectedDate = it.dueDate // Store selected due date
                dateTextView.text = Utils.getReadableDate(it.dueDate) // Display formatted date
            }
        })
    }

     /*-- SAVE BUTTON ACTION --*/
    fun saveBtnClicked(view: View) 
    {
        val taskStr = taskEditText.text.toString().trim() // Retrieve updated task text

        /*-- VALIDATE EMPTY TASK --*/
        if (taskStr.isEmpty()) 
        {
            taskEditText.error = getString(R.string.empty_task_error)
            taskEditText.requestFocus()
        } 
        else 
        {
            /*-- VALIDATE DATE SELECTION --*/
            if (selectedDate == null) 
            {
                Toast.makeText(
                    applicationContext,
                    getString(R.string.empty_date_error),
                    Toast.LENGTH_SHORT
                ).show()
            } 
            else 
            {
                viewModel.updateListItem(taskStr, selectedDate!!, listItemId) // Update task item
                
                // Display success message
                Toast.makeText(applicationContext, getString(R.string.list_item_updated_successfully), Toast.LENGTH_SHORT)
                    .show()

                // Close activity
                finish()
            }
        }
    }

    /*-- DATE PICKER ACTION --*/
    fun dateViewClicked(view: View) 
    {
        // Retrieve current date and time
        val currentDateTime = Calendar.getInstance()
        val startYear = currentDateTime.get(Calendar.YEAR)
        val startMonth = currentDateTime.get(Calendar.MONTH)
        val startDay = currentDateTime.get(Calendar.DAY_OF_MONTH)
        val startHour = currentDateTime.get(Calendar.HOUR_OF_DAY)
        val startMinute = currentDateTime.get(Calendar.MINUTE)

        /*-- DATE PICKER DIALOG --*/
        DatePickerDialog(
            this@EditTaskActivity,
            DatePickerDialog.OnDateSetListener 
            { _, year, month, day ->

                /*-- TIME PICKER DIALOG --*/
                TimePickerDialog(
                    this@EditTaskActivity,
                    TimePickerDialog.OnTimeSetListener 
                    { _, hour, minute ->
                        val pickedDateTime = Calendar.getInstance()  // Create selected date object
                        pickedDateTime.set(year, month, day, hour, minute)
                        selectedDate = pickedDateTime.timeInMillis // Convert date into timestamp
                        dateTextView.text = Utils.getReadableDate(selectedDate!!) // Display formatted date
                    },
                    startHour,
                    startMinute,
                    false
                ).show()
            },
            startYear,
            startMonth,
            startDay
        ).show()
    }

    /*-- TOOLBAR BACK BUTTON HANDLER --*/
    override fun onOptionsItemSelected(@NonNull item: MenuItem): Boolean 
    {
        // Close activity when back button is pressed
        if (android.R.id.home == item.itemId) 
        {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

}
