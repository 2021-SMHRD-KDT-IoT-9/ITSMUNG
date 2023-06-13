package com.example.itsmungapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.itsmungapplication.Adapter.StateAdapter

class StateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_state)

        val rcv : RecyclerView = findViewById(R.id.rcv_state)

        // 데이터 생성
        var states: MutableList<AlarmVO> = mutableListOf()
        states.add(AlarmVO(R.drawable.emergency, "대변 상태 이상 신호 알림"
            ,"", "  ‣ 형태 : 딱딱한 토끼 똥   \n  ‣ 색  :  검붉은색 " ,"" ))

        // 어댑터 생성 및 설정
        val adapter = StateAdapter(this, R.layout.card_layout_state, states)
        rcv.layoutManager = LinearLayoutManager(this)
        rcv.adapter = adapter



    }
}