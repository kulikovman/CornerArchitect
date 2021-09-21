package com.example.cornerarchitect.ui.specialization

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cornerarchitect.R

class SpecializationFragment : Fragment() {

    companion object {
        fun newInstance() = SpecializationFragment()
    }

    private lateinit var viewModel: SpecializationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.specialization_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SpecializationViewModel::class.java)
        // TODO: Use the ViewModel
    }

}