package com.example.taskhumanassignment.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.taskhumanassignment.adapter.SkillAdapter
import com.example.taskhumanassignment.databinding.FragmentDiscoverBinding
import com.example.taskhumanassignment.viewmodel.DiscoverViewModel
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

class DiscoverFragment : Fragment() {

    private val TAG = "DiscoverFragment"
    private lateinit var binding: FragmentDiscoverBinding
    private val viewModel: DiscoverViewModel by viewModels()
    val adapter = SkillAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDiscoverBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //set recyclerview adapter
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = adapter

        viewModel.skills.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "skills: $it")
            adapter.setSkillsList(it)
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "errorMessage: $it")

        })

        viewModel.getSkillsFromNetwork()
    }

}