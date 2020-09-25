package com.example.dostigator

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.dostigator.adapters.TaskListAdapter
import com.example.dostigator.adapters.TaskListListener
import com.example.dostigator.data.AppDatabase
import com.example.dostigator.databinding.TaskListFragmentBinding
import com.example.dostigator.viewmodels.TaskListViewModel
import com.example.dostigator.viewmodels.TaskListViewModelFactory

class TaskListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: TaskListFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.task_list_fragment,
            container,
            false
        )

        val application = requireNotNull(this.activity).application

        val args = TaskListFragmentArgs.fromBundle(requireArguments())

        val dataSource = AppDatabase.getInstance(application).taskDao

        val viewModelFactory = TaskListViewModelFactory(args.planKey, dataSource)

        val viewModel = ViewModelProvider(this, viewModelFactory).get(TaskListViewModel::class.java)

        binding.taskListViewModel = viewModel

        binding.lifecycleOwner = this

        val adapter = TaskListAdapter(TaskListListener { id ->
            viewModel.onTaskListClicked(id)
        })
        binding.taskList.adapter = adapter

        viewModel.clickTaskList.observe(viewLifecycleOwner, { task ->
            task?.let {
                Toast.makeText(context, "$task", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.tasks.observe(viewLifecycleOwner, {
            it?.let {
                adapter.submitList(it)
            }
        })

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.task_list_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}
