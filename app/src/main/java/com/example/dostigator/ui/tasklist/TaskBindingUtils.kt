package com.example.dostigator.ui.tasklist

import android.widget.CheckBox
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.dostigator.database.Task

@BindingAdapter("taskTitle")
fun TextView.setTaskTitle(item: Task?) {
    item?.let {
        text = item.title
    }
}

@BindingAdapter("taskDescription")
fun TextView.setTaskDescription(item: Task?) {
    item?.let {
        text = item.description
    }
}

@BindingAdapter("taskCompleted")
fun CheckBox.setTaskCompleted(item: Task) {
    item.let {
        isChecked = item.isCompleted
    }
}