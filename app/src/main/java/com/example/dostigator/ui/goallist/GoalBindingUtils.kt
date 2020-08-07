package com.example.dostigator.ui.goallist

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.dostigator.R
import com.example.dostigator.database.Goal

@BindingAdapter("goalTitle")
fun TextView.setGoalTitle(item: Goal?) {
    item?.let {
        text = item.title
    }
}

@BindingAdapter("goalImage")
fun ImageView.setGoalImage(item: Goal?) {
    item?.let {
        setImageResource(R.drawable.ic_baseline_photo_24)
    }
}