package com.example.itsmungapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import java.text.DateFormat
import java.util.Calendar


class DogJoinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dog_join)
        var name = intent.getStringExtra("dog_name")
        var gender = intent.getStringExtra("dog_gender")
        var age = intent.getStringExtra("dog_age")
        var species = intent.getStringExtra("dog_species")
        // 견종
        var neutered = intent.getStringExtra("dog_neutered")



//        // TODO : DB에서 반려견의 이름을 가져옵니다.
        val et_dog_join_name : EditText = findViewById(R.id.et_dog_join_name)
        if (name != null) {
            if(name.isNotEmpty()){
                et_dog_join_name.setText(name)
            }
        }

//
//        // TODO : 생년월일 사용자가 전부 입력하도록!  --> datePicker
//        // TODO : 생년월일을 DB에서 가져온다.
        val et_dog_join_year : EditText = findViewById(R.id.et_dog_join_year)
        val et_dog_join_month : EditText = findViewById(R.id.et_dog_join_month)
        val et_dog_join_day : EditText = findViewById(R.id.et_dog_join_day)
        // DB에서 가져온 경우
        // 아래가 DB에 있는 경우
        var birthday : String = ""
        if (birthday.isNotEmpty()) {
            val parts = birthday.split("-") // 년, 월, 일로 분리합니다.
            if (parts.size == 3) {
                val year = parts[0]
                val month = parts[1]
                val day = parts[2]

                et_dog_join_year.setText(year)
                et_dog_join_month.setText(month)
                et_dog_join_day.setText(day)
            }
        } else{
            // 없는 경우 현재 시간을 넣어준다.
            // hint 형태로 값을 입력
            val calendar = Calendar.getInstance().time
            val dateFormat = DateFormat.getDateInstance().format(calendar)
            Log.d("test","왔는지 확인")
            Log.d("dateFormat",dateFormat)
            val parts = dateFormat.split(" ", ",") // 공백과 쉼표를 구분자로 사용하여 문자열 분할
            Log.d("parts",parts.size.toString())
            if (parts.size == 4) {
                val monthString = parts[0] // 영어 형식의 월
                val day = parts[1].toInt() // 일
                val year = parts[3].toInt() // 년

                // 영어 형식의 월을 숫자 형태로 변환
                val month: Int = when (monthString) {
                    "Jan" -> 1
                    "Feb" -> 2
                    "Mar" -> 3
                    "Apr" -> 4
                    "May" -> 5
                    "Jun" -> 6
                    "Jul" -> 7
                    "Aug" -> 8
                    "Sep" -> 9
                    "Oct" -> 10
                    "Nov" -> 11
                    "Dec" -> 12
                    else -> -1 // 유효하지 않은 월
                }
                et_dog_join_year.hint = year.toString()
                et_dog_join_month.hint = month.toString()
                et_dog_join_day.hint =(day.toString())
            }


        }
//
//
        val et_dog_join_breed : EditText = findViewById(R.id.et_dog_join_breed)
        if (species != ""){
            et_dog_join_breed.setText(species)
        }
        val et_dog_join_weight : EditText = findViewById(R.id.et_dog_join_weight)
        // TODO : DB에서 무게 데이터를 가져와서 저장한다.
//           데이터가 없는 경우 0 값을 입력한다.
        var weight : Float = 0f

        et_dog_join_weight.setText(weight.toString())
        val radio_dog_join_man : RadioButton = findViewById(R.id.radio_dog_join_man)
        val radio_dog_join_woman : RadioButton = findViewById(R.id.radio_dog_join_woman)
        val radio_dog_join_reuter : RadioButton = findViewById(R.id.radio_dog_join_reuter)
        val radio_dog_join_nonereuter : RadioButton = findViewById(R.id.radio_dog_join_nonereuter)
        val btn_dog_join_save : Button = findViewById(R.id.btn_dog_join_save)

//
        if(name == ""){
            btn_dog_join_save.setText("등록하기")
        }

//
//
//        // TODO : 강아지 이미지를 모바일 갤러리에서 업로드 할 수 있어야 한다.  imageView에서 수정 HOW?
//
        //  중성화 defalut 또는 사용자 선택 반영
        if (neutered=="" ||neutered == "O"){
            radio_dog_join_reuter.isChecked = true
            radio_dog_join_nonereuter.isChecked = false

        }else{
            radio_dog_join_reuter.isChecked = false
            radio_dog_join_nonereuter.isChecked = true
        }

        // 성별 default 또는 사용자 선택 반영
        if (gender =="" || gender == "남"){
            radio_dog_join_man.isChecked = true
            radio_dog_join_woman.isChecked = false
        }else{
            radio_dog_join_woman.isChecked = true
            radio_dog_join_man.isChecked = false
        }
//
//
        //  중복 선택 제거
        radio_dog_join_man.setOnClickListener{
            radio_dog_join_woman.isChecked = false
            gender = "남"
        }
        radio_dog_join_woman.setOnClickListener{
            radio_dog_join_man.isChecked = false
            gender = "여"
        }

        //  중복 선택 제거
        radio_dog_join_reuter.setOnClickListener {
            // "중성화" 라디오 버튼을 선택한 경우 처리할 코드 작성
            radio_dog_join_nonereuter.isChecked = false // "중성화 안 함" 라디오 버튼 선택 해제
            neutered = "O"
        }
        radio_dog_join_nonereuter.setOnClickListener {
            // "중성화 안 함" 라디오 버튼을 선택한 경우 처리할 코드 작성
            radio_dog_join_reuter.isChecked = false // "중성화" 라디오 버튼 선택 해제
            neutered = "X"

        }


        // 강아지의 이미지 및 데이터를 등록한다.
        btn_dog_join_save.setOnClickListener {
            // TODO : 수정 또는 등록하는 경우 모든 데이터를 입력해야한다.
            // TODO : 입력하지 않은 경우 데이터를 등록 또는 수정하기 실패
            name = et_dog_join_name.text.toString()
            var year = et_dog_join_year.text.toString()
            var month = et_dog_join_month.text.toString()
            var day = et_dog_join_day.text.toString()
            var birthday = year + "-" +month + "-"+day + "-"
            weight = et_dog_join_weight.text.toString().toFloat()
            //종
            species = et_dog_join_breed.text.toString()
            if(radio_dog_join_man.isChecked == true){
                gender = "남"
            }else{
                gender = "여"
            }
            if(radio_dog_join_reuter.isChecked == true){
                neutered = "O"
            }else{
                neutered = "X"
            }
            // TODO : 등록 성공인 경우 DB에 저장
            // DB에 저장할 데이터
            // name, birthday , weight, species, gender, neutered
            val intent = Intent(this@DogJoinActivity, MainActivity::class.java)
            startActivity(intent)
            Toast.makeText(this,"등록 성공",Toast.LENGTH_SHORT).show()
            finish()
            // 처음 등록? , 업데이트 구분?
        }

    }

}