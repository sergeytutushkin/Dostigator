package com.example.dostigator.ui.goallist

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.dostigator.R
import com.example.dostigator.database.source.local.DostigatorDatabase
import com.example.dostigator.databinding.GoalListFragmentBinding


class GoalListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: GoalListFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.goal_list_fragment,
            container,
            false
        )

        val application = requireNotNull(this.activity).application

        val dataSource = DostigatorDatabase.getInstance(application).goalDao

        val viewModelFactory = GoalListViewModelFactory(dataSource, application)

        val viewModel = ViewModelProvider(this, viewModelFactory).get(GoalListViewModel::class.java)

        binding.goalListViewModel = viewModel

        binding.lifecycleOwner = this

        val manager = GridLayoutManager(activity, 2)
        binding.goalList.layoutManager = manager

        val adapter = GoalListAdapter(GoalListListener { id ->
            viewModel.onGoalListClicked(id)
        })
        binding.goalList.adapter = adapter

        viewModel.navigateToPlanList.observe(viewLifecycleOwner, Observer { goal ->
            goal?.let {
                this.findNavController().navigate(
                    GoalListFragmentDirections.actionGoalListFragmentToPlanListFragment(goal)
                )
                viewModel.onPlanListNavigated()
            }
        })

        viewModel.goals.observe(viewLifecycleOwner, Observer {
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
        inflater.inflate(R.menu.goal_list_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}
