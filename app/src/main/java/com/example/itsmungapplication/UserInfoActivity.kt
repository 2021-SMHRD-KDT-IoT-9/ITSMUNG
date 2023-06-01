package com.example.itsmungapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.itsmungapplication.Fragment.MypageFragment

class UserInfoActivity : AppCompatActivity() {
    private lateinit var et_user_info_pw : EditText
    private lateinit var et_user_info_nick : EditText
    private lateinit var et_user_info_tel : EditText
    private lateinit var btn_user_info_save : Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        et_user_info_pw = findViewById(R.id.et_user_info_pw)
        et_user_info_nick = findViewById(R.id.et_user_info_nick)
        et_user_info_tel = findViewById(R.id.et_user_info_tel)
        btn_user_info_save = findViewById(R.id.btn_user_info_save)

        btn_user_info_save.setOnClickListener {
            saveUserInfo()
        }

    }

    private fun saveUserInfo(){
        val et_user_info_pw = et_user_info_pw.text.toString()
        val et_user_info_nick = et_user_info_nick.text.toString()
        val et_user_info_tel = et_user_info_tel.text.toString()

        // MypageFragment로 돌아가는 로직
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val mypageFragment = MypageFragment()
        fragmentTransaction.replace(R.id.fl_user_info_mypage, mypageFragment)
        fragmentTransaction.commit()

        finish() // UserInfoActivity 종료


    }
}