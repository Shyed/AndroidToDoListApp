/*
Project Name: OrgMe
Author: Sheila Demonteverde
Module Name: MyCustomSpinner
Date: 05/28/2020
*/

package com.sheilademonteverde.orgme.ui

import android.content.Context
import android.util.AttributeSet

class MyCustomSpinner : androidx.appcompat.widget.AppCompatSpinner {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    override fun setSelection(position: Int, animate: Boolean) {
        val sameSelected = position == selectedItemPosition
        super.setSelection(position, animate)
        if (sameSelected) {
            // Spinner does not call the OnItemSelectedListener if the same item is selected, so do it manually now
            onItemSelectedListener!!.onItemSelected(
                this,
                selectedView,
                position,
                selectedItemId
            )
        }
    }

    override fun setSelection(position: Int) {
        val sameSelected = position == selectedItemPosition
        super.setSelection(position)
        if (sameSelected) {
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