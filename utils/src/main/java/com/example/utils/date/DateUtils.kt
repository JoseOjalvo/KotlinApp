package com.example.utils.date

import com.example.commons.constants.Constants
import com.example.commons.constants.Constants.COUNTRY
import com.example.commons.constants.Constants.EMPTY_STRING
import com.example.commons.constants.Constants.FORMAT_IN
import com.example.commons.constants.Constants.FORMAT_OUT
import com.example.commons.constants.Constants.REGION
import java.text.DateFormat
import java.text.DateFormat.MEDIUM
import java.text.SimpleDateFormat
import java.util.*

/**
 *   @author Jose Alejandro Ojalvo
 *   @since 02/02/2021
 *   @email jojalvo@topdoctors.es
 **/
object DateUtils {

    /**
     * Parses the date and prints it
     */
    fun setDate(date: String, shouldUseCalendar: Boolean, position: Int): String {
        val dateParser = SimpleDateFormat(FORMAT_IN, Locale(REGION, COUNTRY))
        val dateFormatter = SimpleDateFormat(FORMAT_OUT, Locale(REGION, COUNTRY))
        val dateViewText = DateFormat.getDateInstance(MEDIUM, Locale(REGION, COUNTRY))
        var parsedDate = EMPTY_STRING

        /**
         * Let or safe call works the same as != null, but it returns the value of the variable
         * that was contained at the moment, !! should never be used because it throws an exception
         * if the value is null meanwhile a safe call doesn't, the code below won't be executed
         */
        dateParser.parse(date)?.let {
            parsedDate = dateFormatter.format(it)
        }
        if (shouldUseCalendar) {
            val calendar = Calendar.getInstance()
            dateFormatter.parse(parsedDate)?.let {
                calendar.time = it
            }
            calendar.add(Calendar.DATE, position + 1)
            return dateViewText.format(calendar.time)
        } else {
            dateFormatter.parse(parsedDate)?.let {
                return dateViewText.format(it)
            }
        }
        return EMPTY_STRING
    }
}