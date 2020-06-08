package com.example.dostigator.ui.goals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.dostigator.R
import com.example.dostigator.databinding.GoalListFragmentBinding


class GoalListFragment : Fragment() {

    private lateinit var viewModel: GoalListViewModel
    private lateinit var binding: GoalListFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.goal_list_fragment,
            container,
            false
        )

        viewModel = ViewModelProvider(this).get(GoalListViewModel::class.java)
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
                this.findNavController().navigate(GoalListFragmentDirections.actionGoalListFragmentToPlanListFragment(goal))
                viewModel.onPlanListNavigated()
            }
        })

        viewModel.testList.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        return binding.root
    }
}
