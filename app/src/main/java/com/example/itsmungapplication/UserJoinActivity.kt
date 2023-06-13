package com.example.itsmungapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class UserJoinActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_join)

        sharedPreferences = getSharedPreferences("my_app", Context.MODE_PRIVATE)


        val et_user_join_email : EditText = findViewById(R.id.et_user_join_email)
        val et_user_join_pw : EditText = findViewById(R.id.et_user_join_pw)
        val et_user_join_name : EditText = findViewById(R.id.et_user_join_name)
        val et_user_join_nick : EditText = findViewById(R.id.et_user_join_nick)
        val et_user_join_tel : EditText = findViewById(R.id.et_user_join_tel)
        val btn_user_join_sign : Button = findViewById(R.id.btn_user_join_sign)
        if(intent.getBooleanExtra("kakaoTry",false)){
            et_user_join_email.setText(intent.getStringExtra("kakaoUserId"))
        }
        btn_user_join_sign.setOnClickListener {
            // TODO: 230602 이영재
            //  Join으로 위에서 얻는 데이터를 가져오고 DB에 저장하는 기능 추가 필요

            var email = et_user_join_email.text.toString()
            var pw = et_user_join_pw.text.toString()
            var name = et_user_join_name.text.toString()
            var nick = et_user_join_nick.text.toString()
            var tel = et_user_join_tel.text.toString()
            val UserVO = UserVO()
            UserVO.userId = email
            UserVO.userPw = pw
            UserVO.userName = name
            UserVO.nickname = nick
            UserVO.userTel = tel
            // TODO : UserVO를 활용하여 DB에 저장하는 controler 생성


            // TODO: DB에 회원정보가 저장이 성공되었는지 확인을 한 후 로그인 페이지로 이동합니다.
            // 예시
            var success : Boolean = true
            if(success) {
                val intent = Intent(this@UserJoinActivity, UserLoginActivity::class.java)
                if(intent.getBooleanExtra("kakaoTry",false)){
                    // TODO : kakao에 연계도 등록합니다. 시간도 등록한다.
                    UserVO.kakaoEmail = email
                }
                // SharedPreferences에 회원 정보를 저장합니다.
                val editor = sharedPreferences.edit()
                editor.putString("userId", email)
                editor.putString("userPw", pw)
                editor.putString("userName", name)
                editor.putString("nickname", nick)
                editor.putString("userTel", tel)
                editor.apply()
                startActivity(intent)
                Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT).show()
                finish()
            }else{
                Toast.makeText(this, "회원가입 실패", Toast.LENGTH_SHORT).show()
            }

        }
    }
}