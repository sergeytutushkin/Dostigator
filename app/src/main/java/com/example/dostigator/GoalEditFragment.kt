package com.example.dostigator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.dostigator.data.AppDatabase
import com.example.dostigator.databinding.GoalEditFragmentBinding
import com.example.dostigator.viewmodels.GoalEditViewModel
import com.example.dostigator.viewmodels.GoalEditViewModelFactory

class GoalEditFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: GoalEditFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.goal_edit_fragment,
            container,
            false
        )

        val application = requireNotNull(this.activity).application

        val args = GoalEditFragmentArgs.fromBundle(requireArguments())

        val dataSource = AppDatabase.getInstance(application).goalDao

        val viewModelFactory = GoalEditViewModelFactory(args.goalKey, dataSource)

        val viewModel = ViewModelProvider(this, viewModelFactory).get(GoalEditViewModel::class.java)

        binding.viewModel = viewModel

        binding.lifecycleOwner = this

        viewModel.navigateToGoalList.observe(viewLifecycleOwner, {
            it?.let {
                this.findNavController()
                    .navigate(GoalEditFragmentDirections.actionGoalEditFragmentToGoalListFragment())
                viewModel.doneNavigating()
            }
        })

        return binding.root
    }

}
