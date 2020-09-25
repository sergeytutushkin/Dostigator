package com.example.dostigator.adapters

import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.dostigator.R
import com.example.dostigator.data.Goal
import com.example.dostigator.data.Plan
import com.example.dostigator.data.Task

@BindingAdapter("goalTitle")
fun TextView.setGoalTitle(item: Goal?) {
    item?.let {
//        text = "${item.title}, ${item.description}, ${item.id}"
        text = item.title
    }
}

@BindingAdapter("goalImage")
fun ImageView.setGoalImage(item: Goal?) {
    item?.let {
        setImageResource(R.drawable.ic_baseline_photo_24)
    }
}

@BindingAdapter("goalCompleted")
fun CheckBox.setGoalCompleted(item: Goal?) {
    item?.let {
        isChecked = item.isCompleted
    }
}

@BindingAdapter("planTitle")
fun TextView.setPlanTitle(item: Plan?) {
    item?.let {
        text = item.title
    }
}

@BindingAdapter("planDescription")
fun TextView.setPlanDescription(item: Plan?) {
    item?.let {
        text = item.description
    }
}

@BindingAdapter("planCompleted")
fun CheckBox.setPlanCompleted(item: Plan) {
    item.let {
        isChecked = item.isCompleted
    }
}

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