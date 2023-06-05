package com.example.itsmungapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.itsmungapplication.Adapter.NoticeAdapter

class NoticeActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice)

        viewManager = LinearLayoutManager(this)
        viewAdapter = NoticeAdapter()

        recyclerView = findViewById<RecyclerView>(R.id.rcv_notice).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter

        }
    }
}