package com.example.itsmungapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton

class UserLoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_login)

        val et_btn_id : EditText = findViewById(R.id.et_user_login_id)
        val et_btn_pw : EditText = findViewById(R.id.et_user_login_pw)
        val btn_login : Button = findViewById(R.id.btn_user_login_login)
        val btn_kakao_login : ImageButton = findViewById(R.id.btn_kakao_login)













    }
}