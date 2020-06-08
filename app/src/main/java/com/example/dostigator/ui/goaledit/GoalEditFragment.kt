package com.example.dostigator.ui.goaledit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.dostigator.R

class GoalEditFragment : Fragment() {

    companion object {
        fun newInstance() = GoalEditFragment()
    }

    private lateinit var viewModel: GoalEditViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.goal_edit_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GoalEditViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
