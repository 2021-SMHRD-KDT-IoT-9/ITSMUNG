package com.example.itsmungapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.itsmungapplication.adapter.ExpertfragAdapter
import com.example.itsmungapplication.R

class ExpertFragment : Fragment() {
    private lateinit var expertFragAdapter: ExpertfragAdapter
    private val messageList = mutableListOf<String>()
    private lateinit var recycler_expertf: RecyclerView
    private lateinit var btn_expertf_submit: Button
    private lateinit var et_expertf_write: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_expert, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        expertFragAdapter = ExpertfragAdapter(messageList)

        recycler_expertf = view.findViewById(R.id.recycler_expertf)
        btn_expertf_submit = view.findViewById(R.id.btn_expertf_submit)
        et_expertf_write = view.findViewById(R.id.et_expertf_write)

        recycler_expertf.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = expertFragAdapter
        }

        btn_expertf_submit.setOnClickListener {
            val message = et_expertf_write.text.toString()
            if (message.isNotEmpty()) {
                messageList.add(message)
                expertFragAdapter.notifyItemInserted(messageList.size - 1)
                et_expertf_write.text.clear()
            }
        }
    }
}