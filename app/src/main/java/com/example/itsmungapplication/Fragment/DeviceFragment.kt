package com.example.itsmungapplication.Fragment

import android.content.ClipData.Item
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.itsmungapplication.Adapter.DevicefragAdapter
import com.example.itsmungapplication.R

class DeviceFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DevicefragAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_device, container, false)

        // RecyclerView 설정
        recyclerView = view.findViewById(R.id.rcv_devicef)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Adapter 생성 및 연결
        //adapter = DevicefragAdapter(getItemList())
        recyclerView.adapter = adapter

        return view
    }

    // RecyclerView에 표시할 아이템 리스트 반환
    private fun getItemList(): List<Item> {
        // 아이템 리스트 생성
        val itemList = mutableListOf<Item>()
        // 필요한 아이템을 itemList에 추가
        //itemList.add(Item(R.drawable., "Item 1"))
        //itemList.add(Item(R.drawable., "Item 2"))
        //...

        return itemList
    }


}
