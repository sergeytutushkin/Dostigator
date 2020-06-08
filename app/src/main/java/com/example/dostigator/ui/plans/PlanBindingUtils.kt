package com.example.dostigator.ui.plans

import android.widget.CheckBox
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.dostigator.database.Plan

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