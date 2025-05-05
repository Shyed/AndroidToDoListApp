/*
Project Name: OrgMe
Author: Sheila Demonteverde
Module Name: Utils
Date: 05/28/2020
*/

package com.sheilademonteverde.orgme.ui

import java.text.SimpleDateFormat
import java.util.*

object Utils {

    fun getReadableDate(d: Long): String {
        val mFormat = SimpleDateFormat("EEEE d,MMM yyyy ' at ' h:mm a", Locale.ENGLISH)
        return mFormat.format(d)
    }


}