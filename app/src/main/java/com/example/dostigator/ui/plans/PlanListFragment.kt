package com.example.dostigator.ui.plans

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.dostigator.R
import com.example.dostigator.databinding.PlanListFragmentBinding

class PlanListFragment : Fragment() {

    private lateinit var viewModel: PlanListViewModel
    private lateinit var binding: PlanListFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.plan_list_fragment,
            container,
            false
        )

        val args = PlanListFragmentArgs.fromBundle(requireArguments())
        Toast.makeText(context, "${args.goalKey}", Toast.LENGTH_SHORT).show()

        viewModel = ViewModelProvider(this).get(PlanListViewModel::class.java)
        binding.planListViewModel = viewModel

        binding.lifecycleOwner = this

        val adapter = PlanListAdapter(PlanListListener { id ->
            viewModel.onPlanListClicked(id)
        })
        binding.planList.adapter = adapter

        viewModel.navigateToTaskList.observe(viewLifecycleOwner, Observer { plan ->
            plan?.let {
                this.findNavController().navigate(PlanListFragmentDirections.actionPlanListFragmentToTaskListFragment(plan))
                viewModel.onTaskListNavigated()
            }
        })

        viewModel.testList.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
//
//        val application = requireNotNull(activity).application
//        val db = DostigatorDatabase.getInstance(application)
//
//        val planDao = db.planDao
//        val plan = Plan(goalId = 1)
//        plan.title = "План 1"
//        plan.description = "Первый тестовый план"
//        val planId = planDao.insert(plan)
//
//        adapter.data = planDao.getAll()

        return binding.root
    }

}
