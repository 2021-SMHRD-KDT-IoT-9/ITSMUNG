package com.example.itsmungapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.example.itsmungapplication.api.ApiManager
import com.example.itsmungapplication.api.ApiService
import com.example.itsmungapplication.api.MatchingRequest
import com.example.itsmungapplication.fragment.ExpertFragment
import com.example.itsmungapplication.fragment.HomeFragment
import com.example.itsmungapplication.fragment.MypageFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var bnv : BottomNavigationView
    private lateinit var fl : FrameLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bnv = findViewById(R.id.bnv)
        fl = findViewById(R.id.fl)

        // 내부에 저장된 userId값 가져오기
        val sharedPreferences = getSharedPreferences("itsmung", Context.MODE_PRIVATE)
        val userId = sharedPreferences.getString("userId", null)

        // 구독서비스 하는지 여부 체크
        val request = MatchingRequest(userId)
        ApiManager.match(request)
        { response ->
            if (response != null) {
                if (response.match) {
                    bnv.menu.clear()  // 기존 메뉴 삭제
                    bnv.inflateMenu(R.menu.menu_item2)  // menu_item2.xml 메뉴 적용
                } else {
                    bnv.menu.clear()  // 기존 메뉴 삭제
                    bnv.inflateMenu(R.menu.menu_item)  // menu_item.xml 메뉴 적용
                }
            } else {
                Toast.makeText(this, "비 구독자입니다", Toast.LENGTH_SHORT).show()
            }
        }

        supportFragmentManager.beginTransaction().replace(
            R.id.fl,
            HomeFragment()
        ).commit()


        bnv.setOnItemSelectedListener {
            when(it.itemId)
            {
                R.id.tab1->
                { // 메인 페이지
                        supportFragmentManager.beginTransaction().replace(
                            R.id.fl,
                            HomeFragment()
                        ).commit()
                }
                /*R.id.tab2->{ // 기기관리
                        supportFragmentManager.beginTransaction().replace(
                            R.id.fl,
                            DeviceFragment()
                        ).commit()
                }*/
                R.id.tab3->
                { // 전문가 페이지
                        supportFragmentManager.beginTransaction().replace(
                            R.id.fl,
                            ExpertFragment()
                        ).commit()
                    // do
                }
                R.id.tab4->
                { // 마이 페이지
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fl,
                        MypageFragment()
                    ).commit()
                    // do
                }

           }
            true
        }

    }


}

