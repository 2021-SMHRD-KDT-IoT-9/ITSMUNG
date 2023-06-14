package com.example.itsmungapplication

import android.annotation.SuppressLint
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.itsmungapplication.api.ApiManager
import com.example.itsmungapplication.api.JoinRequest
import com.example.itsmungapplication.api.UserInfoRequest
import com.example.itsmungapplication.api.UserUpdateRequest
import com.example.itsmungapplication.fragment.MypageFragment
import com.example.itsmungapplication.vo.UserVO

class UserInfoActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var et_user_info_pw : EditText
    private lateinit var et_user_info_nick : EditText
    private lateinit var et_user_info_tel : EditText
    private lateinit var btn_user_info_save : Button

    private lateinit var tv_user_info_id : TextView
    private lateinit var tv_user_info_name : TextView

    private lateinit var user : UserVO

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        tv_user_info_id = findViewById(R.id.tv_user_info_id)
        tv_user_info_name = findViewById(R.id.tv_user_info_name)
        et_user_info_pw = findViewById(R.id.et_user_info_pw)
        et_user_info_nick = findViewById(R.id.et_user_info_nick)
        et_user_info_tel = findViewById(R.id.et_user_info_tel)
        btn_user_info_save = findViewById(R.id.btn_user_info_save)

        sharedPreferences = getSharedPreferences("itsmung", MODE_PRIVATE)
        val userId = sharedPreferences.getString("userId", null)

        var userName : String? = ""
        var userPw : String? = ""
        var nickname : String? = ""
        var userTel : String? = ""

        val userVO = UserVO()
        userVO.userId = userId

        val userInfoRequest = UserInfoRequest(userVO)

        ApiManager.userInfo(userInfoRequest)
        {
            response->
            if(response != null)
            {
                user = response.user
                userName = user.userName
                userPw = user.userPw
                nickname = user.nickname
                userTel = user.userTel

                tv_user_info_id.setText(userId)
                tv_user_info_name.setText(userName)
                et_user_info_pw.setText(userPw)
                et_user_info_nick.setText(nickname)
                et_user_info_tel.setText(userTel)
            }
        }



        btn_user_info_save.setOnClickListener {
            saveUserInfo(user)
        }

    }

    private fun saveUserInfo(user : UserVO)
    {
        user.userPw = et_user_info_pw.text.toString()
        user.nickname = et_user_info_nick.text.toString()
        user.userTel = et_user_info_tel.text.toString()


        // TODO : 가져온 내용을 DB에 update한다.
        val userUpdateRequest = UserUpdateRequest(user)
        Log.e("request", "${userUpdateRequest}")
        ApiManager.userUpdate(userUpdateRequest)
        { response->
            Log.e("response", "response : ${response}")
            if(response != null)
            {
                val fragmentManager = supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                val mypageFragment = MypageFragment()
                fragmentTransaction.replace(R.id.fl_user_info_mypage, mypageFragment)
                fragmentTransaction.commit()

                finish() // UserInfoActivity 종료
                Toast.makeText(this,"정보 수정 성공", Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(this, "정보 수정 실패", Toast.LENGTH_SHORT).show()
            }

        }
        // MypageFragment로 돌아가는 로직


    }
}