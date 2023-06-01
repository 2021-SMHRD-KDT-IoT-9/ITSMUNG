package com.example.itsmungapplication

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter

class WeightActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weight)

        // TODO : 최근 일주일 데이터를 가져와서 입력해야한다. 이 부분 작성 누구???
        // 그래프 뷰 초기화
        val chart: LineChart = findViewById(R.id.chart)
        val xAxis: XAxis = chart.xAxis
        val List_localDateStr: List<String> = listOf("06.01", "06.02", "06.03", "06.04")
        val formatter: ValueFormatter = IndexAxisValueFormatter(List_localDateStr)
        xAxis.valueFormatter = formatter

        // X축 아래에 표시
        xAxis.position = XAxis.XAxisPosition.BOTTOM

//        // 축을 숫자가 아니라 날짜로 표시
//        xAxis.setValueFormatter(new IndexAxisValueFormatter(List_localDateStr));


        // 데이터 생성
        val entries = arrayListOf<Entry>()
        entries.add(Entry(0f, 5f * 1000f))
        entries.add(Entry(1f, 5.15f * 1000f))
        entries.add(Entry(2f, 5.10f * 1000f))
        entries.add(Entry(3f, 5.20f * 1000f))


        // 데이터셋 생성
        val dataSet = LineDataSet(entries, "Label")
        dataSet.setColors(Color.BLUE)
        dataSet.valueTextColor = Color.BLACK

        // 라인 데이터 생성 및 설정
        val lineData = LineData(dataSet)
        chart.data = lineData

        // 그래프 업데이트
        chart.invalidate()
    }
}