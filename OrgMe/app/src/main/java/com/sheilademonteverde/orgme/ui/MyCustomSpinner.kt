/*
=====================================================
 PROJECT: Android To Do List App (OrgMe)
 MODULE: ui/MyCustomSpinner
 AUTHOR: Sheila Demonteverde
 DATE: 05/28/2020
 Updated Dev Notes: 05/12/2026

 DESCRIPTION:
 This custom Spinner class extends AppCompatSpinner
 to handle repeated item selections properly.

 FEATURES:
 - Custom spinner behavior
 - Manual item selection triggering
 - Handles repeated selections
 - Supports animated and non-animated selection

 NOTES:
 - Default Spinner does not trigger
   OnItemSelectedListener when selecting
   the same item repeatedly
=====================================================
*/

package com.sheilademonteverde.orgme.ui

import android.content.Context
import android.util.AttributeSet

/*-- CUSTOM SPINNER CLASS --*/
class MyCustomSpinner : androidx.appcompat.widget.AppCompatSpinner 
{
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

     /*-- ANIMATED SELECTION HANDLER --*/
    override fun setSelection(position: Int, animate: Boolean) 
    {
        val sameSelected = position == selectedItemPosition // Check if selected item is already selected
        super.setSelection(position, animate) // Perform default selection behavior

        /*-- MANUAL ITEM SELECTION CALLBACK --*/
        if (sameSelected) 
        {
            // Spinner does not call the OnItemSelectedListener if the same item is selected, so do it manually now
            onItemSelectedListener!!.onItemSelected(
                this,
                selectedView,
                position,
                selectedItemId
            )
        }
    }
/*-- STANDARD SELECTION HANDLER --*/
    override fun setSelection(position: Int) 
    {
        val sameSelected = position == selectedItemPosition // Check if selected item is already selected
        super.setSelection(position) // Perform default selection behavior

        /*-- MANUAL ITEM SELECTION CALLBACK --*/
        if (sameSelected) 
        {
            // Spinner does not call the OnItemSelectedListener if the same item is selected, so do it manually now
            onItemSelectedListener!!.onItemSelected(
                this,
                selectedView,
                position,
                selectedItemId
            )
        }
    }
}
