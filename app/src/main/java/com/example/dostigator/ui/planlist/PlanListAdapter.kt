package com.example.dostigator.ui.planlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dostigator.database.Plan
import com.example.dostigator.databinding.ListItemPlanBinding

class PlanListAdapter(private val clickListener: PlanListListener) : ListAdapter<Plan, PlanListAdapter.ViewHolder>(PlanListDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: ListItemPlanBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Plan, clickListener: PlanListListener) {
            binding.plan = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemPlanBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class PlanListDiffCallback : DiffUtil.ItemCallback<Plan>() {
    override fun areItemsTheSame(oldItem: Plan, newItem: Plan): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Plan, newItem: Plan): Boolean {
        return oldItem == newItem
    }

}

class PlanListListener(val clickListener: (id: Long) -> Unit) {
    fun onClick(plan: Plan) = clickListener(plan.id)
}
