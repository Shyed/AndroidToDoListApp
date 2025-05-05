/*
Project Name: OrgMe
Author: Sheila Demonteverde
Module Name: AddNewTaskToList
Date: 05/28/2020
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

class AddNewTaskToList : AppCompatActivity() {

    private lateinit var taskEditText: EditText
    private lateinit var dateTextView: TextView
    private var selectedDate: Long? = null
    private var itemListId = 0

    private val viewModel: AddNewTaskViewModel by lazy {
        ViewModelProviders.of(this).get(AddNewTaskViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_task_to_list)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.add_task_text)

        taskEditText = findViewById(R.id.taskEditText)
        dateTextView = findViewById(R.id.dateTextView)

        itemListId = intent.getIntExtra("itemListId", 0)
    }

    fun saveBtnClicked(view: View) {
        val taskStr = taskEditText.text.toString().trim()
        if (taskStr.isEmpty()) {
            taskEditText.error = getString(R.string.empty_task_error)
            taskEditText.requestFocus()
        } else {
            if (selectedDate == null) {
                Toast.makeText(
                    applicationContext,
                    getString(R.string.empty_date_error),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                viewModel.addListItem(taskStr, selectedDate!!, itemListId)
                Toast.makeText(applicationContext, getString(R.string.task_added_successfully), Toast.LENGTH_SHORT)
                    .show()
                finish()
            }
        }
    }

    fun dateViewClicked(view: View) {
        val currentDateTime = Calendar.getInstance()
        val startYear = currentDateTime.get(Calendar.YEAR)
        val startMonth = currentDateTime.get(Calendar.MONTH)
        val startDay = currentDateTime.get(Calendar.DAY_OF_MONTH)
        val startHour = currentDateTime.get(Calendar.HOUR_OF_DAY)
        val startMinute = currentDateTime.get(Calendar.MINUTE)

        DatePickerDialog(
            this@AddNewTaskToList,
            DatePickerDialog.OnDateSetListener { _, year, month, day ->
                TimePickerDialog(
                    this@AddNewTaskToList,
                    TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                        val pickedDateTime = Calendar.getInstance()
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

    override fun onOptionsItemSelected(@NonNull item: MenuItem): Boolean {
        if (android.R.id.home == item.itemId) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

}
