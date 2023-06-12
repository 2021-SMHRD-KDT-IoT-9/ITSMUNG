package com.example.itsmungapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.itsmungapplication.Fragment.MypageFragment
import org.w3c.dom.Text

class UserInfoActivity : AppCompatActivity() {
    private lateinit var et_user_info_pw : EditText
    private lateinit var et_user_info_nick : EditText
    private lateinit var et_user_info_tel : EditText
    private lateinit var btn_user_info_save : Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)
        val tv_user_info_id : TextView = findViewById(R.id.tv_user_info_id)
        val tv_user_info_name : TextView = findViewById(R.id.tv_user_info_name)
        et_user_info_pw = findViewById(R.id.et_user_info_pw)
        et_user_info_nick = findViewById(R.id.et_user_info_nick)
        et_user_info_tel = findViewById(R.id.et_user_info_tel)
        btn_user_info_save = findViewById(R.id.btn_user_info_save)

        // TODO : DB에서 가져온 정보를 입력한다.
        var id : String = "youngjae9683@gmail.com"
        tv_user_info_id.setText(id)
        var name : String = "이영재"
        tv_user_info_name.setText(name)
        var pw : String = "1234"
        et_user_info_pw.setText(pw)
        var nick : String = "Sarami"
        et_user_info_nick.setText(nick)
        var tel : String = "010-1234-1234"
        et_user_info_tel.setText(tel)

        btn_user_info_save.setOnClickListener {
            saveUserInfo()
        }

    }

    private fun saveUserInfo(){
        val et_user_info_pw = et_user_info_pw.text.toString()
        val et_user_info_nick = et_user_info_nick.text.toString()
        val et_user_info_tel = et_user_info_tel.text.toString()

        // TODO : 가져온 내용을 DB에 update한다.
        // MypageFragment로 돌아가는 로직
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val mypageFragment = MypageFragment()
        fragmentTransaction.replace(R.id.fl_user_info_mypage, mypageFragment)
        fragmentTransaction.commit()

        finish() // UserInfoActivity 종료


    }
}