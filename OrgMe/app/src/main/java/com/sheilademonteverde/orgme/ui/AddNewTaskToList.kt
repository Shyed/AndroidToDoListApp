/*
=====================================================
 PROJECT: Android To Do List App (OrgMe)
 MODULE: ui/AddNewTaskToList
 AUTHOR: Sheila Demonteverde
 DATE: 05/28/2020
 Updated Dev Notes: 05/12/2026

 DESCRIPTION:
 This activity allows users to create and save
 new tasks into a selected task list.

 FEATURES:
 - Task input validation
 - Date and time selection
 - Task saving using ViewModel
 - Toast notifications
 - Toolbar back navigation

 NOTES:
 - Uses MVVM architecture
 - Uses DatePickerDialog and TimePickerDialog
 - Stores selected due dates as timestamps
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
import androidx.lifecycle.ViewModelProviders
import com.sheilademonteverde.orgme.R
import com.sheilademonteverde.orgme.viewModels.AddNewTaskViewModel
import java.util.*

/*-- ADD NEW TASK ACTIVITY --*/
class AddNewTaskToList : AppCompatActivity() 
{
    /*-- UI COMPONENTS --*/
    private lateinit var taskEditText: EditText
    private lateinit var dateTextView: TextView

    /*-- TASK VARIABLES --*/
    private var selectedDate: Long? = null
    private var itemListId = 0

     /*-- VIEWMODEL INITIALIZATION --*/
    private val viewModel: AddNewTaskViewModel by lazy 
    {
        ViewModelProviders.of(this).get(AddNewTaskViewModel::class.java)
    }

    /*-- ACTIVITY CREATION --*/
    override fun onCreate(savedInstanceState: Bundle?) 
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_task_to_list) // Load activity layout

        supportActionBar?.setDisplayHomeAsUpEnabled(true) // Enable toolbar back button
        supportActionBar?.title = getString(R.string.add_task_text) // Set toolbar title

        taskEditText = findViewById(R.id.taskEditText) // Connect UI components
        dateTextView = findViewById(R.id.dateTextView)

        itemListId = intent.getIntExtra("itemListId", 0) // Retrieve selected task list ID
    }

    /*-- SAVE TASK BUTTON ACTION --*/
    fun saveBtnClicked(view: View) 
    {
        val taskStr = taskEditText.text.toString().trim() // Retrieve task text

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
                viewModel.addListItem(taskStr, selectedDate!!, itemListId) // Save task using ViewModel

                // Display success message
                Toast.makeText(applicationContext, getString(R.string.task_added_successfully), Toast.LENGTH_SHORT)
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
            this@AddNewTaskToList,
            DatePickerDialog.OnDateSetListener 
            { _, year, month, day ->

                /*-- TIME PICKER DIALOG --*/
                TimePickerDialog(
                    this@AddNewTaskToList,
                    TimePickerDialog.OnTimeSetListener 
                    { _, hour, minute ->

                        val pickedDateTime = Calendar.getInstance() // Create selected date/time object
                        pickedDateTime.set(year, month, day, hour, minute)
                        selectedDate = pickedDateTime.timeInMillis // Convert date into timestamp
                        dateTextView.text = Utils.getReadableDate(selectedDate!!) // Display readable formatted date
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
