package com.example.taskhumanassignment.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.taskhumanassignment.R
import com.example.taskhumanassignment.viewmodel.DiscoverViewModel

class MessagesFragment : Fragment() {

    companion object {
        fun newInstance() = MessagesFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_messages, container, false)
    }

}