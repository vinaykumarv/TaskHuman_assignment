package com.example.taskhumanassignment.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

object Util {

    @SuppressLint("SimpleDateFormat")
    fun getTimeFromMilliseconds(timeInMillis: Long?): String {

        if(timeInMillis != null) {
            val myDate = Date(timeInMillis)
            val formatter = SimpleDateFormat("HH:mm")
            return formatter.format(myDate)
        }
        return "";
    }

    fun convertSecondsToHMmSs(seconds: Long?): String {
        if(seconds != null) {
            val s = seconds % 60
            val m = seconds / 60 % 60
            val h = seconds / (60 * 60) % 24
            return String.format("%d:%02d:%02d", h, m, s)
        }
        return ""
    }
}