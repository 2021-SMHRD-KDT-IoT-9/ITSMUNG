
package com.example.itsmungapplication

import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter

class WeightActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weight)





        // TODO: 전체 데이터 가져오기 (DB에서 가져오거나 가상의 데이터 리스트로 대체)
        val dataList = getAllData()

        // TODO: 중복된 날짜의 무게 평균 계산
        val averageDataMap = calculateAverageData(dataList)

        // TODO: 날짜와 평균 무게 데이터 추출 및 정렬
        val sortedDataList = averageDataMap.toList().sortedBy { (date, _) -> date }
        val dateList = ArrayList<String>()
        val weightList = ArrayList<Float>()



        for (entry in sortedDataList) {
            val date = entry.first
            val weight = entry.second

            // 날짜에서 월과 일 추출
            val parts = date.split("-")
            val month = parts[1]
            val day = parts[2]
            val formattedDate = "$month-$day"

            dateList.add(formattedDate)
            weightList.add(weight)
        }
        // 오늘의 무게 표시
        val tv_weight_weight : TextView = findViewById(R.id.tv_weight_weight)
        tv_weight_weight.setText(weightList[weightList.size-1].toString()+"kg")
        // 그래프 뷰 초기화
        val chart: LineChart = findViewById(R.id.chart)
        chart.description.isEnabled = false
        chart.legend.isEnabled = false

        // X축 설정
        val xAxis: XAxis = chart.xAxis
        val formatter: ValueFormatter = object : ValueFormatter() {
            override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                val index = value.toInt()
                return dateList.getOrNull(index) ?: ""
            }
        }
        xAxis.valueFormatter = formatter
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(false)

        // Y축 설정
        val yAxis: YAxis = chart.axisLeft
        yAxis.valueFormatter = object : ValueFormatter() {
            override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                return String.format("%.2f", value) // Y축 값 소수점 2자리까지 표시
            }
        }
        yAxis.setDrawGridLines(false)
        yAxis.setDrawAxisLine(false)
        yAxis.setDrawLabels(true)

        // 오른쪽 Y축 비활성화
        val rightYAxis: YAxis = chart.axisRight
        val leftYAxis: YAxis = chart.axisLeft
        rightYAxis.isEnabled = false
//        leftYAxis.isEnabled = false


        // 데이터 생성
        val entries = ArrayList<Entry>()
        for (i in weightList.indices) {
            entries.add(Entry(i.toFloat(), weightList[i]))
        }

        // 데이터셋 생성
        val dataSet = LineDataSet(entries, "Weight")
        dataSet.setColors(Color.BLUE)
        dataSet.valueTextColor = Color.BLACK
        dataSet.setDrawValues(true) // 좌표에 값 표시
        dataSet.valueFormatter = object : ValueFormatter() {
            override fun getPointLabel(entry: Entry?): String {
                return String.format("%.2f", entry?.y) // 좌표에 표시할 값 소수점 2자리까지 표시
            }
        }
        dataSet.valueTextSize = 10f
        dataSet.setDrawCircleHole(false) // 좌표에 점 표시
        dataSet.setDrawIcons(true)
        dataSet.setDrawHighlightIndicators(false)
        dataSet.setDrawVerticalHighlightIndicator(false)
        dataSet.setDrawHorizontalHighlightIndicator(false)


        // 라인 데이터 생성 및 설정
        val lineData = LineData(dataSet)
        chart.data = lineData

        // 그래프 스크롤 가능하도록 설정
        chart.isDragEnabled = true
        chart.setScaleEnabled(false)
        chart.setPinchZoom(false)

        // X축 스크롤 제한 설정
        chart.setVisibleXRangeMaximum(6f) // 7일치 데이터만 표시
        chart.moveViewToX(entries.size.toFloat() - 1) // 최근 데이터로 스크롤
        // 그래프 그리드 설정
        chart.xAxis.setDrawGridLines(true)
        chart.xAxis.gridColor = Color.LTGRAY
        chart.axisLeft.setDrawGridLines(true)
        chart.axisLeft.gridColor = Color.LTGRAY
        // 그래프 업데이트
        chart.invalidate()
    }

    private fun getAllData(): List<Pair<String, Float>> {
        // 예시 데이터 반환 (가상의 데이터 또는 DB에서 가져온 데이터로 대체)
        return listOf(
            Pair("2023-06-01", 5.0f),
            Pair("2023-06-02", 5.3f),
            Pair("2023-06-03", 5.1f),
            Pair("2023-06-09", 5.2f),
            Pair("2023-06-10", 5.3f),
            Pair("2023-06-11", 5.6f),

            Pair("2023-06-06", 6.4f),
            Pair("2023-06-07", 6.4f),
            Pair("2023-06-04", 5.2f),
            Pair("2023-06-05", 5.3f),
            Pair("2023-06-06", 5.4f),
            Pair("2023-06-07", 5.4f),
            Pair("2023-06-08", 5.5f),
            Pair("2023-06-08", 6.5f),
            Pair("2023-06-09", 6.2f),

            Pair("2023-06-10", 6.3f),
            Pair("2023-06-11", 6.6f),
            Pair("2023-06-12", 6.7f),
            Pair("2023-06-13", 6.4f),
            Pair("2023-06-12", 5.7f),
            Pair("2023-06-13", 5.4f),
            Pair("2023-06-14", 5.8f),
            Pair("2023-06-01", 6.0f),
            Pair("2023-06-02", 6.3f),
            Pair("2023-06-03", 6.1f),
            Pair("2023-06-04", 6.2f),
            Pair("2023-06-05", 6.3f),

            Pair("2023-06-14", 6.8f)
        )
    }

    private fun calculateAverageData(dataList: List<Pair<String, Float>>): Map<String, Float> {
        val averageDataMap = HashMap<String, Float>()
        val dateWeightMap = HashMap<String, MutableList<Float>>()

        for (data in dataList) {
            val date = data.first
            val weight = data.second

            if (dateWeightMap.containsKey(date)) {
                // 중복된 날짜인 경우 리스트에 추가
                dateWeightMap[date]?.add(weight)
            } else {
                // 새로운 날짜인 경우 리스트 생성 후 값 추가
                dateWeightMap[date] = mutableListOf(weight)
            }
        }

        for (entry in dateWeightMap.entries) {
            val date = entry.key
            val weightList = entry.value
            val averageWeight = weightList.average().toFloat()

            averageDataMap[date] = averageWeight
        }

        return averageDataMap
    }
}