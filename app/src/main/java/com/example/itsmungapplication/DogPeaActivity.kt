package com.example.itsmungapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import android.widget.TextView

class DogPeaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dog_pea)

        val tv_dogpea : TextView = findViewById(R.id.tv_dogpea)
        val cal_dogpea : CalendarView = findViewById(R.id.cal_dogpea)

        cal_dogpea.setOnDateChangeListener { view, year, month, dayOfMonth ->
            var year = year
            var month = month + 1
            var days = dayOfMonth

            // 횟수 카운팅을 통해 사용자에게 보여주는 것을 수정
            var count : Int = days
            // TODO : 위의 날짜 데이터를 토대로 그 날짜의 오줌 횟수 데이터를 가져옵니다.
            // count에 횟수 저장
            var peaCount : String = ""
            for (i in 0 until count) {
                peaCount += "\uD83E\uDD43"
            }

            tv_dogpea.setText(peaCount)

        }

    }
}