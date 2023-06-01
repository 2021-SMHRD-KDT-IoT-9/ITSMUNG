package com.example.itsmungapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import android.widget.TextView

class DogStoolActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dogstool)

        val tv_dogstool : TextView = findViewById(R.id.tv_dogstool)
        val cal_dogstool : CalendarView = findViewById(R.id.cal_dogstool)

        cal_dogstool.setOnDateChangeListener { view, year, month, dayOfMonth ->
            var year = year
            var month = month + 1
            var days = dayOfMonth
            
            // 횟수 카운팅을 통해 사용자에게 보여주는 것을 수정
            var count : Int = days
            // TODO : 위의 날짜 데이터를 토대로 그 날짜의 똥 싼 횟수 데이터를 가져옵니다.
            // count에 횟수 저장
            var stoolCount : String = ""
            for (i in 0 until count) {
                stoolCount += "\uD83D\uDCA9"
            }

            tv_dogstool.setText(stoolCount)

        }


    }
}