/*
=====================================================
 PROJECT: OrgMe
 MODULE: AddNewTaskToList
 AUTHOR: Sheila Demonteverde
 DATE: 05/28/2020
 Dev Notes added: 05/12/2026 - SD

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
 - Designed for Android task management application
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

class AddNewTaskToList : AppCompatActivity() 
{
    /*-- UI COMPONENTS --*/
    private lateinit var taskEditText: EditText
    private lateinit var dateTextView: TextView

    /*-- TASK DATA VARIABLES --*/
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
        setContentView(R.layout.activity_add_new_task_to_list)

        supportActionBar?.setDisplayHomeAsUpEnabled(true) // Enable back button in toolbar
        supportActionBar?.title = getString(R.string.add_task_text) // Connect UI elements

        taskEditText = findViewById(R.id.taskEditText)
        dateTextView = findViewById(R.id.dateTextView)

        itemListId = intent.getIntExtra("itemListId", 0) // Retrieve selected task list ID from previous activity
    }

    /*-- SAVE TASK BUTTON ACTION --*/
    fun saveBtnClicked(view: View) 
    {
        val taskStr = taskEditText.text.toString().trim() // Retrieve and trim task text

        // Validate empty task input    
        if (taskStr.isEmpty()) 
        {
            taskEditText.error = getString(R.string.empty_task_error)
            taskEditText.requestFocus()
        } 
        else 
        {
            // Validate selected date
            if (selectedDate == null) {
                Toast.makeText(
                    applicationContext,
                    getString(R.string.empty_date_error),
                    Toast.LENGTH_SHORT
                ).show()
            } 
            else 
            {
                // Save task using ViewModel
                viewModel.addListItem(taskStr, selectedDate!!, itemListId)

                // Display success message
                Toast.makeText(applicationContext, getString(R.string.task_added_successfully), Toast.LENGTH_SHORT)
                    .show()

                // Close current activity
                finish()
            }
        }
    }

    /*-- DATE SELECTION ACTION --*/
    fun dateViewClicked(view: View) 
    {
        val currentDateTime = Calendar.getInstance() // Get current date and time
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
                    TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                        val pickedDateTime = Calendar.getInstance()  // Store selected date and time
                        pickedDateTime.set(year, month, day, hour, minute)
                        selectedDate = pickedDateTime.timeInMillis
                        dateTextView.text = Utils.getReadableDate(selectedDate!!)
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
         // Close activity when back button is press
        if (android.R.id.home == item.itemId) 
        {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

}
