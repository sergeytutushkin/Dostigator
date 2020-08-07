package com.example.dostigator.ui.planlist

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.dostigator.R
import com.example.dostigator.database.source.local.DostigatorDatabase
import com.example.dostigator.databinding.PlanListFragmentBinding

class PlanListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: PlanListFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.plan_list_fragment,
            container,
            false
        )

        val application = requireNotNull(this.activity).application

        val args = PlanListFragmentArgs.fromBundle(requireArguments())
//        Toast.makeText(context, "${args.goalKey}", Toast.LENGTH_SHORT).show()

        val dataSource = DostigatorDatabase.getInstance(application).planDao

        val viewModelFactory = PlanListViewModelFactory(args.goalKey, dataSource)

        val viewModel = ViewModelProvider(this, viewModelFactory).get(PlanListViewModel::class.java)

        binding.planListViewModel = viewModel

        binding.lifecycleOwner = this

        val adapter = PlanListAdapter(PlanListListener { id ->
            viewModel.onPlanListClicked(id)
        })
        binding.planList.adapter = adapter

        viewModel.navigateToTaskList.observe(viewLifecycleOwner, Observer { plan ->
            plan?.let {
                this.findNavController().navigate(
                    PlanListFragmentDirections.actionPlanListFragmentToTaskListFragment(plan)
                )
                viewModel.onTaskListNavigated()
            }
        })

        viewModel.plans.observe(viewLifecycleOwner, Observer {
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
        inflater.inflate(R.menu.plan_list_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}
