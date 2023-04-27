package com.example.taskhumanassignment.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.taskhumanassignment.R
import com.example.taskhumanassignment.viewmodel.DiscoverViewModel

class ReconnectFragment : Fragment() {

    companion object {
        fun newInstance() = ReconnectFragment()
    }

    //private lateinit var viewModel: DiscoverViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //viewModel = ViewModelProvider(this).get(DiscoverViewModel::class.java)
        return inflater.inflate(R.layout.fragment_reconnect, container, false)
    }

}