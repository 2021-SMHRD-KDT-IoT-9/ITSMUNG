package com.example.itsmungapplication.Fragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.itsmungapplication.NoticeActivity
import com.example.itsmungapplication.R
import com.example.itsmungapplication.StateActivity
import com.example.itsmungapplication.UserInfoActivity
import com.example.itsmungapplication.UserLoginActivity
import com.kakao.sdk.user.UserApiClient
import android.content.ContentValues.TAG
class MypageFragment : Fragment() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var context: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.context = context
    }
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_mypage, container, false)

        val btn_mypage_change: Button = view.findViewById(R.id.btn_mypage_change)
        val btn_mypage_logout : Button = view.findViewById(R.id.btn_mypage_logout)
        val imgbtn_mypage_state : ImageButton = view.findViewById(R.id.imgbtn_mypage_state)
        val imgbtn_mypage_notice : ImageButton = view.findViewById((R.id.imgbtn_mypage_notice))
        // 반려견 이름
        val tv_mypage_dogUserName : TextView = view.findViewById(R.id.tv_mypage_dogusername)
        val tv_mypage_userName : TextView = view.findViewById(R.id.tv_mypage_username)
        val tv_mypage_userTel : TextView = view.findViewById(R.id.tv_mypage_usertel)
        val tv_mypage_userAccount : TextView = view.findViewById(R.id.tv_mypage_useraccount)

        sharedPreferences = context.getSharedPreferences("my_app", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        // 사용자 로그인 상태 확인 및 처리

        
        // TODO : 사용자의 정보를 가져옵니다.(DB에서)
        var dogName : String = "소다"
        tv_mypage_dogUserName.setText(dogName + "보호자님")
        var name : String = "이영재"
        tv_mypage_userName.setText(name + " 님 \uD83C\uDF80")
        var tel : String = "010-1234-5678"
        tv_mypage_userTel.setText(tel)
        // 카카오 어떻게 아이디를 보여줄 것인가? 정말 연계된 아이디를 보여줄 것인가?
        tv_mypage_userAccount.setText("")



        // 사용자 정보 수정하기
        btn_mypage_change.setOnClickListener {
            val intent = Intent(activity, UserInfoActivity::class.java)
            startActivity(intent)
        }

        // Logout 을 위한 기능
        btn_mypage_logout.setOnClickListener {
            // Logout 을 위한 기능
            editor.putBoolean("isLoggedIn", false)
            editor.remove("lastLoginTime")
            editor.apply()
            // 로그아웃
            // kakao SDK 삭제
            UserApiClient.instance.logout { error ->
                if (error != null) {
                    Log.e(TAG, "로그아웃 실패. SDK에서 토큰 삭제됨", error)
                }
                else {
                    Log.i(TAG, "로그아웃 성공. SDK에서 토큰 삭제됨")
                }
            }

            val intent = Intent(activity,UserLoginActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }

        // state (강아지 상태)알림 페이지로 전환
        imgbtn_mypage_state.setOnClickListener {
            val intent = Intent(activity,StateActivity::class.java)
            startActivity(intent)
        }

        // notice (공지사항) 알림 페이지로 전환
        imgbtn_mypage_notice.setOnClickListener {
            val intent = Intent(activity,NoticeActivity::class.java)
            startActivity(intent)
        }


        return view
    }

}
