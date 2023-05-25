package com.example.itsmungapplication.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.itsmungapplication.DeviceVO
import com.example.itsmungapplication.R


class DevicewifiFragment : Fragment() {
    private lateinit var RecyclerView: RecyclerView
    //private lateinit var DeviceAdapter: DeviceAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
        ): View? {
            // Inflate the layout for this fragment
            val view =  inflater.inflate(R.layout.fragment_devicewifi, container, false)
            val rcv : RecyclerView = view.findViewById(R.id.rcv)
            val wifiList : MutableList<DeviceVO> = mutableListOf()
            wifiList.add(DeviceVO("itmung"))
            wifiList.add(DeviceVO("smhrd_A반"))
            wifiList.add(DeviceVO("smhrd_B반"))
            wifiList.add(DeviceVO("smhrd_C반"))

//            DeviceAdapter = DeviceAdapter(wifiList)
//            rcv.layoutManager = LinearLayoutManager(requireContext())
//            rcv.adapter = DeviceAdapter



        return view
    }



}