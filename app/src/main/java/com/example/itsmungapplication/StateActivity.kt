package com.example.itsmungapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.itsmungapplication.Adapter.NoticeAdapter
import com.example.itsmungapplication.Adapter.StateAdapter

class StateActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_state)

        val rcv_state : RecyclerView = findViewById(R.id.rcv_state)

        var alarm: MutableList<AlarmVO> = mutableListOf()
//        alarm.add(AlarmVO(R.drawable.pawprint, "대변에 이상이 보입니다."," 딱딱한 토끼 똥 형태 : 변비 \n 빨간색 변 : 항문 질병 및 위장 출혈의 의심됩니다. \n 병원 내원을 추천합니다."))

        // 4. Adapter : ViewHolder패턴 파일만 만들고 먼저 지정해주기
        val adapter = StateAdapter(applicationContext, R.layout.card_layout_state, alarm )
        // 화면에 어떻게 나오게 할건지(가로,세로 / )
        rcv_state.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        rcv_state.adapter = adapter



    }
}