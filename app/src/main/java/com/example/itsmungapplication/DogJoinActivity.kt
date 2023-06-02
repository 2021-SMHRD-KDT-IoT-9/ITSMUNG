package com.example.itsmungapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton

class DogJoinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dog_join)
        var name = intent.getStringExtra("dog_name")
        var age = intent.getIntExtra("dog_age", 0)
        var gender = intent.getStringExtra("dog_gender")
        var species = intent.getStringExtra("dog_species")
        // 견종
        var neutered = intent.getStringExtra("dog_neutered")
        

        val et_dog_join_name : EditText = findViewById(R.id.et_dog_join_name)
        // TODO : 생년월일 사용자가 전부 입력하도록!  --> datePicker
        // TODO : 생년월일을 DB에서 가져온다.
        //val et_dog_join_birthday : EditText = findViewById(R.id.et_dog_join_birthday)
        val et_dog_join_breed : EditText = findViewById(R.id.et_dog_join_breed)
        val et_dog_join_weight : EditText = findViewById(R.id.et_dog_join_weight)
        val radio_dog_join_man : RadioButton = findViewById(R.id.radio_dog_join_man)
        val radio_dog_join_woman : RadioButton = findViewById(R.id.radio_dog_join_woman)
        val radio_dog_join_reuter : RadioButton = findViewById(R.id.radio_dog_join_reuter)
        val radio_dog_join_nonereuter : RadioButton = findViewById(R.id.radio_dog_join_nonereuter)
        val btn_dog_join_save : Button = findViewById(R.id.btn_dog_join_save)


        if(name == ""){
            btn_dog_join_save.setText("등록하기")
        }



        // TODO : 강아지 이미지를 모바일 갤러리에서 업로드 할 수 있어야 한다.  imageView에서 수정 HOW?

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
            // ...
        }
        radio_dog_join_nonereuter.setOnClickListener {
            // "중성화 안 함" 라디오 버튼을 선택한 경우 처리할 코드 작성
            radio_dog_join_reuter.isChecked = false // "중성화" 라디오 버튼 선택 해제
            neutered = "X"

            // ...
        }


        // 강아지의 이미지 및 데이터를 등록한다.
        btn_dog_join_save.setOnClickListener {
            // TODO : 수정 또는 등록하는 경우 모든 데이터를 입력해야한다.
            // TODO : 입력하지 않은 경우 데이터를 등록 또는 수정하기 실패


            // TODO : 등록 성공인 경우 DB에 저장
        }

    }
}