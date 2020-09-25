package com.example.dostigator.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dostigator.data.Goal
import com.example.dostigator.databinding.ListItemGoalBinding

class GoalListAdapter(
    private val itemClickListener: ItemClickListener,
    private val editClickListener: EditClickListener,
    private val deleteClickListener: DeleteClickListener,
    private val completeClickListener: CompleteClickListener
) :
    ListAdapter<Goal, GoalListAdapter.ViewHolder>(
        GoalListDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(
            item,
            itemClickListener,
            editClickListener,
            deleteClickListener,
            completeClickListener
        )
    }

    class ViewHolder private constructor(private val binding: ListItemGoalBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: Goal,
            itemClickListener: ItemClickListener,
            editClickListener: EditClickListener,
            deleteClickListener: DeleteClickListener,
            completeClickListener: CompleteClickListener
        ) {
            binding.goal = item
            binding.itemClickListener = itemClickListener
            binding.editClickListener = editClickListener
            binding.deleteClickListener = deleteClickListener
            binding.completeClickListener = completeClickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemGoalBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class GoalListDiffCallback : DiffUtil.ItemCallback<Goal>() {
    override fun areItemsTheSame(oldItem: Goal, newItem: Goal): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Goal, newItem: Goal): Boolean {
        return oldItem == newItem
    }

}

class ItemClickListener(val clickListener: (id: Long) -> Unit) {
    fun onClick(goal: Goal) = clickListener(goal.id)
}

class EditClickListener(val editListener: (id: Long) -> Unit) {
    fun onEdit(goal: Goal) = editListener(goal.id)
}

class DeleteClickListener(val deleteListener: (id: Long) -> Unit) {
    fun onDelete(goal: Goal) = deleteListener(goal.id)
}

class CompleteClickListener(val completeListener: (id: Long) -> Unit) {
    fun onComplete(goal: Goal) = completeListener(goal.id)

//    val lt: ListenerType = ListenerType.ItemClickListener(1)
}

sealed class ListenerType {
    //    class ItemClickListener(val clickListener: (id: Long) -> Unit) : ListenerType()
    class ItemClickListener(val id: Long) : ListenerType()
    class EditClickListener(val id: Long) : ListenerType()
    class DeleteClickListener(val id: Long) : ListenerType()
    class CompleteClickListener(val id: Long) : ListenerType()
//    class ItemClickListener(val id: Long, clickListener: (Long) -> Unit) :
//        ListenerType(clickListener)
//
//    class EditClickListener(val id: Long, clickListener: (Long) -> Unit) :
//        ListenerType(clickListener)
//
//    class DeleteClickListener(clickListener: (Long) -> Unit) : ListenerType(clickListener)
//
//    class CompleteClickListener(clickListener: (Long) -> Unit) : ListenerType(clickListener)
}

interface UpdateClickListener {
    fun onUpdateClicked(clicked: ListenerType)
}