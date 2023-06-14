package com.example.itsmungapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat
import com.example.itsmungapplication.api.ApiManager
import com.example.itsmungapplication.api.DogInfoRequest
import com.example.itsmungapplication.api.DogJoinRequest
import com.example.itsmungapplication.api.DogUpdateRequest
import com.example.itsmungapplication.vo.DogVO
import com.example.itsmungapplication.vo.UserVO
import java.text.DateFormat
import java.util.Calendar


class DogJoinActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var tv_join_title: TextView
    private lateinit var et_dog_join_name: EditText
    private lateinit var et_dog_join_year: EditText
    private lateinit var et_dog_join_month: EditText
    private lateinit var et_dog_join_day: EditText
    private lateinit var et_dog_join_breed: EditText
    private lateinit var et_dog_join_weight: EditText

    private lateinit var radio_dog_join_man: RadioButton
    private lateinit var radio_dog_join_woman: RadioButton
    private lateinit var radio_dog_join_reuter: RadioButton
    private lateinit var radio_dog_join_nonereuter: RadioButton
    private lateinit var btn_dog_join_save: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dog_join)

        tv_join_title = findViewById(R.id.tv_join_title)
        et_dog_join_name = findViewById(R.id.et_dog_join_name)
        et_dog_join_year = findViewById(R.id.et_dog_join_year)
        et_dog_join_month = findViewById(R.id.et_dog_join_month)
        et_dog_join_day = findViewById(R.id.et_dog_join_day)
        et_dog_join_breed = findViewById(R.id.et_dog_join_breed)
        et_dog_join_weight = findViewById(R.id.et_dog_join_weight)

        radio_dog_join_man = findViewById(R.id.radio_dog_join_man)
        radio_dog_join_woman = findViewById(R.id.radio_dog_join_woman)
        radio_dog_join_reuter = findViewById(R.id.radio_dog_join_reuter)
        radio_dog_join_nonereuter = findViewById(R.id.radio_dog_join_nonereuter)

        btn_dog_join_save = findViewById(R.id.btn_dog_join_save)

        sharedPreferences = getSharedPreferences("itsmung", MODE_PRIVATE)
        val token = sharedPreferences.getString("token", null)
        val userId = sharedPreferences.getString("userId", null)

        var dogId = ""
        var name: String = ""
        var gender: String = ""
        var species: String = ""
        var neutered: String = ""
        var weight: Float = 0f
        var birthday: String = ""
        var isNewDog: Boolean = true

        val user = UserVO()
        user.userId = userId

        val request = DogInfoRequest(user)

        // 있는 경우 개의 정보를 미리 넣어주기, 없으면 비우기
        ApiManager.dogInfo(request)
        { response ->
            if (response != null) {
                isNewDog = false
                val dog = response.dog
                dogId = dog.dogId
                name = dog.dogName
                gender = dog.dogGender
                species = dog.dogBreed
                birthday = dog.dogBirth
                weight = dog.dogWeight.toFloat()
                neutered = dog.dogNeutered

                val parts = birthday.split("-") // 년, 월, 일로 분리합니다.
                val year = parts[0]
                val month = parts[1]
                val day = parts[2]

                tv_join_title.setText("댕댕이 정보 변경 🐾")
                et_dog_join_name.setText(name)
                et_dog_join_breed.setText(species)
                et_dog_join_weight.setText(weight.toString())
                et_dog_join_year.setText(year)
                et_dog_join_month.setText(month)
                et_dog_join_day.setText(day)

                genderCheck(gender)
                neuteredCheck(neutered)

            }
            else
            {
                // 없는 경우 현재 시간을 넣어준다.
                // hint 형태로 값을 입력
                val calendar = Calendar.getInstance().time
                val dateFormat = DateFormat.getDateInstance().format(calendar)
                Log.d("test", "왔는지 확인")
                Log.d("dateFormat", dateFormat)
                val parts = dateFormat.split(" ", ",") // 공백과 쉼표를 구분자로 사용하여 문자열 분할
                Log.d("parts", parts.size.toString())
                if (parts.size == 4)
                {
                    val monthString = parts[0] // 영어 형식의 월
                    val day = parts[1].toInt() // 일
                    val year = parts[3].toInt() // 년

                    // 영어 형식의 월을 숫자 형태로 변환
                    val month: Int =
                        when (monthString) {
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
                    et_dog_join_day.hint = (day.toString())
                }
                btn_dog_join_save.setText("등록하기")
                et_dog_join_name.setText(name)
                et_dog_join_breed.setText(species)
                et_dog_join_weight.setText(weight.toString())

                genderCheck(gender)
                neuteredCheck(neutered)
            }

            //  중복 선택 제거
            radio_dog_join_man.setOnClickListener {
                radio_dog_join_woman.isChecked = false
            }
            radio_dog_join_woman.setOnClickListener {
                radio_dog_join_man.isChecked = false
            }

            //  중복 선택 제거
            radio_dog_join_reuter.setOnClickListener{
                // "중성화" 라디오 버튼을 선택한 경우 처리할 코드 작성
                radio_dog_join_nonereuter.isChecked = false // "중성화 안 함" 라디오 버튼 선택 해제
            }
            radio_dog_join_nonereuter.setOnClickListener {
                // "중성화 안 함" 라디오 버튼을 선택한 경우 처리할 코드 작성
                radio_dog_join_reuter.isChecked = false // "중성화" 라디오 버튼 선택 해제
            }


            // 강아지의 이미지 및 데이터를 등록한다.
            btn_dog_join_save.setOnClickListener {

                // dogName, dogBirth, dogGender, dogBreed, dogNeutered, dogWeight
                val dog = DogVO()
                dog.dogName = et_dog_join_name.text.toString()

                var year = et_dog_join_year.text.toString()
                var month = et_dog_join_month.text.toString()
                var day = et_dog_join_day.text.toString()
                dog.dogBirth = year + "-" + month + "-" + day + "-"

                dog.dogWeight = et_dog_join_weight.text.toString().toFloat()
                dog.dogBreed = et_dog_join_breed.text.toString()
                if (radio_dog_join_man.isChecked == true) {
                    dog.dogGender = "Male"
                } else {
                    dog.dogGender = "Female"
                }
                if (radio_dog_join_reuter.isChecked == true) {
                    dog.dogNeutered = "O"
                } else {
                    dog.dogNeutered = "X"
                }

                if(isNewDog)
                {
                    // 애견 등록하기
                    val request = DogJoinRequest(dog, userId)

                    ApiManager.dogJoin(request)
                    {
                        response->
                        if (response != null) 
                        {
                            val intent = Intent(this@DogJoinActivity, MainActivity::class.java)
                            startActivity(intent)
                            Toast.makeText(this, "등록 성공", Toast.LENGTH_SHORT).show()
                            finish()
                        }
                        else
                        {
                            val intent = Intent(this@DogJoinActivity, MainActivity::class.java)
                            startActivity(intent)
                            Toast.makeText(this, "등록 실패", Toast.LENGTH_SHORT).show()
                            finish()
                        }
                    }
                }
                else
                {
                    // 애견 정보 변경하기
                    dog.dogId = dogId
                    val request = DogUpdateRequest(dog, userId)

                    ApiManager.dogUpdate(request)
                    {
                        response->
                        if (response != null)
                        {
                            val intent = Intent(this@DogJoinActivity, MainActivity::class.java)
                            startActivity(intent)
                            Toast.makeText(this, "변경 성공", Toast.LENGTH_SHORT).show()
                            finish()
                        }
                        else
                        {
                            val intent = Intent(this@DogJoinActivity, MainActivity::class.java)
                            startActivity(intent)
                            Toast.makeText(this, "변경 실패", Toast.LENGTH_SHORT).show()
                            finish()
                        }
                    }

                }
            }
        }
    }
    private fun genderCheck(gender : String)
    {
        // 성별 default 또는 사용자 선택 반영
        if (gender == "" || gender == "Male")
        {
            radio_dog_join_man.isChecked = true
            radio_dog_join_woman.isChecked = false
        }
        else
        {
            radio_dog_join_woman.isChecked = true
            radio_dog_join_man.isChecked = false
        }
    }

    private fun neuteredCheck(neutered : String)
    {
        //  중성화 defalut 또는 사용자 선택 반영
        if (neutered == "" || neutered == "O")
        {
            radio_dog_join_reuter.isChecked = true
            radio_dog_join_nonereuter.isChecked = false

        }
        else
        {
            radio_dog_join_reuter.isChecked = false
            radio_dog_join_nonereuter.isChecked = true
        }
    }
}