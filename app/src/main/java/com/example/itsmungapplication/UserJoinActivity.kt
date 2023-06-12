package com.example.itsmungapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.itsmungapplication.api.ApiManager
import com.example.itsmungapplication.api.JoinRequest
import com.example.itsmungapplication.vo.UserVO

class UserJoinActivity : AppCompatActivity() {

    private lateinit var et_user_join_email : EditText
    private lateinit var et_user_join_pw : EditText
    private lateinit var et_user_join_name : EditText
    private lateinit var et_user_join_nick : EditText
    private lateinit var et_user_join_tel : EditText
    private lateinit var btn_user_join_sign : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_join)


        et_user_join_email = findViewById(R.id.et_user_join_email)
        et_user_join_pw = findViewById(R.id.et_user_join_pw)
        et_user_join_name = findViewById(R.id.et_user_join_name)
        et_user_join_nick = findViewById(R.id.et_user_join_nick)
        et_user_join_tel = findViewById(R.id.et_user_join_tel)
        btn_user_join_sign = findViewById(R.id.btn_user_join_sign)


        if(intent.getBooleanExtra("kakaoTry",false)){
            et_user_join_email.setText(intent.getStringExtra("kakaoUserId"))
        }

        btn_user_join_sign.setOnClickListener {

            var userId = et_user_join_email.text.toString()
            var userPw = et_user_join_pw.text.toString()
            var userName = et_user_join_name.text.toString()
            var nickname = et_user_join_nick.text.toString()
            var userTel = et_user_join_tel.text.toString()

            val user = UserVO()
            user.userId = userId
            user.userPw = userPw
            user.userName = userName
            user.nickname = nickname
            user.userTel = userTel

            val request = JoinRequest(user)

            ApiManager.join(request)
            {
                response->
                if(response != null)
                {
                    val intent = Intent(this@UserJoinActivity, UserLoginActivity::class.java)
                    if(intent.getBooleanExtra("kakaoTry",false))
                    {
                        // TODO : kakao에 연계도 등록합니다. 시간도 등록한다.
                        user.kakaoEmail = userId
                    }
                    startActivity(intent)
                    Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT).show()
                    finish()
                }
                else
                {
                    Toast.makeText(this, "회원가입 실패", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}