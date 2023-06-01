package com.example.itsmungapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton

class UserLoginActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_login)

        val et_btn_id : EditText = findViewById(R.id.et_user_login_id)
        val et_btn_pw : EditText = findViewById(R.id.et_user_login_pw)
        val btn_login : Button = findViewById(R.id.btn_user_login_login)
        val btn_join : Button = findViewById(R.id.btn_user_login_join)
        // 카카오 로그인을 위한 부분
        val btn_kakao_login : ImageButton = findViewById(R.id.btn_kakao_login)



        // 06/01 이영재
        // 사용자의 로그인 상태를 저장하기 위한 값입니다.
        // 모바일 내부에 파일 형태로 저장합니다.
        sharedPreferences = getSharedPreferences("my_app", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        // 사용자 로그인 상태 확인 및 처리
//        checkLoginStatus()


        btn_login.setOnClickListener {
            var id : String = et_btn_id.text.toString()
            var pw : String = et_btn_pw.text.toString()

            // TODO: DB에 회원 id와  비밀번호를 확인합니다.
            // TODO: select 해서 가져오는 값이 있는 경우 true를 반환합니다. 없는 경우 로그인에 실패합니다.


            // test 예시
             if(id == "test" && pw == "1234"){
                    // 사용자가 로그인 아이디를 저

                    editor.putString("user_id", id) // "user_id"라는 키에 아이디를 저장
                    editor.putBoolean("isLoggedIn",true)
                    editor.putLong("lastLoginTime", System.currentTimeMillis()) // 사용자가 로그인한 시간을 저장
                    editor.apply()

                    val intent = Intent(this@UserLoginActivity,
                        MainActivity::class.java)
                    startActivity(intent)
                     // 이동하고 stack 삭제
                    finish()
            }

        }
        btn_join.setOnClickListener {
            val intent = Intent(this@UserLoginActivity, UserJoinActivity::class.java)
            startActivity(intent)
        }

    }
    
    
    // login check 함수
    private fun checkLoginStatus() {

        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)
        val lastLoginTime = sharedPreferences.getLong("lastLoginTime", 0)

        if (isLoggedIn) {
            val currentTime = System.currentTimeMillis()
            val elapsedTime = currentTime - lastLoginTime
            val sevenDaysInMillis = 7 * 24 * 60 * 60 * 1000 // 7일을 밀리초로 변환

            if (elapsedTime > sevenDaysInMillis) {
                // 사용자가 7일 동안 로그인하지 않은 경우, 로그인 정보 삭제
                editor.putBoolean("isLoggedIn", false)
                editor.remove("lastLoginTime")
                editor.apply()
            } else {
                // 사용자가 7일 이내에 로그인한 경우, 메인 화면으로 이동
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }
}

