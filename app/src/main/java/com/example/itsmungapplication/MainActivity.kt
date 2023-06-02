package com.example.itsmungapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import com.example.itsmungapplication.fragment.DoctorMainFragment
import com.example.itsmungapplication.fragment.DoctorMypageFragment
import com.example.itsmungapplication.fragment.LiveChatFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bnv: BottomNavigationView = findViewById(R.id.bnv)
        val fl: FrameLayout = findViewById(R.id.fl)

        // 첫화면(실행시) 보이게
        supportFragmentManager.beginTransaction().replace(
            R.id.fl,
            DoctorMainFragment()
        ).commit()

        bnv.setOnItemSelectedListener {
            // it --> 내가 클릭한 item의 아이디, 속성 .. 정보를 받아온다
            // it.itemId : 내가 클릭한 항목의 id
            when (it.itemId) {
                R.id.tab1 -> {
                    // 프래그먼트 매니저가 관리
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fl,
                        DoctorMainFragment()
                    ).commit()
                    // replace() : 화면을 대체
                    // 1) 어디에 : FrameLayout
                    // 2) 뭘로 : HomeFramnet
                }

                R.id.tab2 -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fl,
                        DoctorMypageFragment()
                    ).commit()
                }

                R.id.tab3 -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fl,
                        LiveChatFragment()
                    ).commit()
                }

            }
            // click 이벤트가 끝나지 않았다고 판단 false
            //  false면 다음 클릭을 해도 색이 변경되지 않음
            // true : 클립이벤트가 끝났다 다음 클릭으로 넘어가라
            true //return
        }
    }
}