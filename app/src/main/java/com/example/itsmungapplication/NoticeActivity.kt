package com.example.itsmungapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.itsmungapplication.Adapter.NoticeAdapter

class NoticeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice)

        val rcv: RecyclerView = findViewById(R.id.rcv_notice)

        // 데이터 생성
        val notices: MutableList<AlarmVO> = mutableListOf()
        notices.add(AlarmVO(R.drawable.notice1, "시스템 정기 점검 안내", ""," 금주 주말에 시스템 정기 정검이 있습니다. \n 작업 시간 동안 시스템 접속이 \n 원활하지 않을 수 있습니다.",""))
        notices.add(AlarmVO(R.drawable.notice1, "새로운 업데이트 알림", "","앱이 업데이트되었습니다. \n 새로운 기능과 개선 사항을 확인해보세요.",""))
        notices.add(AlarmVO(R.drawable.change_circle, "물 수위가 낮음", "","물을 채워주시고 \n 오수통을 비워주세요.",""))

        // 어댑터 생성 및 설정
        val adapter = NoticeAdapter(this, R.layout.card_layout_notice, notices)
        rcv.layoutManager = LinearLayoutManager(this)
        rcv.adapter = adapter
    }
}