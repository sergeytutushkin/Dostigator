package com.example.dostigator.ui.tasklist

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.dostigator.R
import com.example.dostigator.database.source.local.DostigatorDatabase
import com.example.dostigator.databinding.TaskListFragmentBinding

class TaskListFragment : Fragment() {

//    private lateinit var viewModel: TaskListViewModel

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

        val dataSource = DostigatorDatabase.getInstance(application).taskDao

        val viewModelFactory = TaskListViewModelFactory(args.planKey, dataSource)

        val viewModel = ViewModelProvider(this, viewModelFactory).get(TaskListViewModel::class.java)

        binding.taskListViewModel = viewModel

        binding.lifecycleOwner = this

        val adapter = TaskListAdapter(TaskListListener { id ->
            viewModel.onTaskListClicked(id)
        })
        binding.taskList.adapter = adapter

        viewModel.clickTaskList.observe(viewLifecycleOwner, Observer { task ->
            task?.let {
                Toast.makeText(context, "${task}", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.tasks.observe(viewLifecycleOwner, Observer {
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
