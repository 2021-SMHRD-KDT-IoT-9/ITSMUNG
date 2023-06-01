package com.example.itsmungapplication

import android.content.Context
import android.content.Intent
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
        val btn_join : Button = findViewById(R.id.btn_user_login_join)
        val btn_kakao_login : ImageButton = findViewById(R.id.btn_kakao_login)



        btn_login.setOnClickListener {
            var id : String = et_btn_id.text.toString()
            var pw : String = et_btn_pw.text.toString()

            // DB에 회원가 비밀번호가 있는지 확인합니다.
            // select 해서 가져오는 값이 있는 경우 true를 반환합니다. 없는 경우 로그인에 실패합니다.
            if(id == "test" && pw == "1234"){
                // 사용자가 로그인 아이디를 저장
                val sharedPreferences = getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("user_id", id) // "user_id"라는 키에 아이디를 저장
                editor.apply()

                val intent = Intent(this@UserLoginActivity,
                    MainActivity::class.java)



                startActivity(intent)

            }

        }










    }
}