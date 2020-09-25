package com.example.dostigator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.dostigator.viewmodels.TaskEditViewModel

class TaskEditFragment : Fragment() {

    companion object {
        fun newInstance() = TaskEditFragment()
    }

    private lateinit var viewModel: TaskEditViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.task_edit_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TaskEditViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
