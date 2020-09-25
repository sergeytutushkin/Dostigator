package com.example.dostigator

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.dostigator.adapters.*
import com.example.dostigator.data.AppDatabase
import com.example.dostigator.databinding.GoalListFragmentBinding
import com.example.dostigator.viewmodels.GoalListViewModel
import com.example.dostigator.viewmodels.GoalListViewModelFactory


class GoalListFragment : Fragment() {

    lateinit var viewModel: GoalListViewModel

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

        val dataSource = AppDatabase.getInstance(application).goalDao

        val viewModelFactory = GoalListViewModelFactory(dataSource)

        viewModel = ViewModelProvider(this, viewModelFactory).get(GoalListViewModel::class.java)

        binding.goalListViewModel = viewModel

        binding.lifecycleOwner = this

        val manager = GridLayoutManager(activity, 2)
        binding.goalList.layoutManager = manager

        val adapter = GoalListAdapter(
            ItemClickListener { id -> viewModel.onGoalListClicked(id) },
            EditClickListener { id -> viewModel.onGoalEditClicked(id) },
            DeleteClickListener { id -> viewModel.onGoalListClicked(id) },
            CompleteClickListener { id -> viewModel.onGoalListClicked(id) })

        binding.goalList.adapter = adapter

        viewModel.navigateToPlanList.observe(viewLifecycleOwner, { goal ->
            goal?.let {
                this.findNavController().navigate(
                    GoalListFragmentDirections.actionGoalListFragmentToPlanListFragment(goal)
                )
                viewModel.onPlanListNavigated()
            }
        })

        viewModel.navigateToGoalEdit.observe(viewLifecycleOwner, { goal ->
            goal?.let {
                this.findNavController().navigate(
                    GoalListFragmentDirections.actionGoalListFragmentToGoalEditFragment(goal)
                )
                viewModel.onGoalEditNavigated()
            }
        })

        viewModel.goals.observe(viewLifecycleOwner, {
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

//    override fun onUpdateClicked(clicked: ListenerType) {
//        when (clicked) {
//            is ListenerType.ItemClickListener -> {
//                viewModel.onGoalListClicked(clicked.id)
//            }
//            is ListenerType.CompleteClickListener -> {
//
//            }
//            is ListenerType.EditClickListener -> {
//
//            }
//            is ListenerType.DeleteClickListener -> {
//
//            }
//        }
//    }
}
