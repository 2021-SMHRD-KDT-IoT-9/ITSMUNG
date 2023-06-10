package com.example.itsmungapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.itsmungapplication.fragment.DoctorMainFragment

class DoctorrInfoActivity : AppCompatActivity() {
    private lateinit var et_pw: EditText
    private lateinit var et_tel : EditText
    private lateinit var btn_save : Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctorr_info)

        val tv_email : TextView = findViewById(R.id.tv_doctor_info_email)
        val tv_name : TextView = findViewById(R.id.tv_doctor_info_name)
        val tv_license : TextView = findViewById(R.id.tv_doctor_info_license)

        et_pw = findViewById(R.id.et_doctor_info_pw)
        et_tel = findViewById(R.id.et_doctor_info_tel)
        btn_save = findViewById(R.id.btn_doctor_info_save)

        // TODO : DB에서 가져온 정보를 입력한다.
        var id : String = "Younghyun@google.com"
        tv_email.setText(id)
        var name : String = "이영현"
        tv_name.setText(name)
        var pw : String = "12345"
        et_pw.setText(pw)
        var license : String = "Sarami"
        tv_license.setText(license)
        var tel : String = "010-1234-1234"
        et_tel.setText(tel)

        btn_save.setOnClickListener {
            saveUserInfo()
        }

    }

    private fun saveUserInfo(){
        val et_doctor_info_pw = et_pw.text.toString()
        val et_doctor_info_tel = et_tel.text.toString()

        // TODO : 가져온 내용을 DB에 update한다.
        // MypageFragment로 돌아가는 로직
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val doctormainFragment = DoctorMainFragment()
        fragmentTransaction.replace(R.id.fl_doctor_info_mypage, doctormainFragment)
        fragmentTransaction.commit()

        finish() // UserInfoActivity 종료


    }
}
