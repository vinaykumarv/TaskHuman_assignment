package com.example.taskhumanassignment.extensions

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter(value = ["isVisible", "isGone"], requireAll = false)
fun View.setVisibility(isVisible: Boolean = false, isGone: Boolean = true) {
    this.visibility = if (isGone) {
        if (isVisible) View.VISIBLE else View.GONE
    } else {
        if (isVisible) View.VISIBLE else View.INVISIBLE
    }
}