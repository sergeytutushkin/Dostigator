package com.example.dostigator.ui.planedit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.dostigator.R

class PlanEditFragment : Fragment() {

    companion object {
        fun newInstance() = PlanEditFragment()
    }

    private lateinit var viewModel: PlanEditViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.plan_edit_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PlanEditViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
