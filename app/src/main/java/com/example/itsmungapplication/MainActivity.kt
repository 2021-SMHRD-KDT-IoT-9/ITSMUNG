package com.example.itsmungapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.FragmentTransaction
import com.example.itsmungapplication.Fragment.DeviceFragment
import com.example.itsmungapplication.Fragment.HomeFragment
import com.example.itsmungapplication.Fragment.MypageFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 내부에 저장된 user_id(DB에서 가져오기용)
        val sharedPreferences = getSharedPreferences("my_app", Context.MODE_PRIVATE)
        val userId = sharedPreferences.getString("user_id", null)
        
        

        val bnv : BottomNavigationView = findViewById(R.id.bnv)
        val fl : FrameLayout = findViewById(R.id.fl)
        val destination = intent.getStringExtra("destination")

        if (destination == "home") {
            val homeFragment = HomeFragment() // HomeFragment로 변경해야하는 프래그먼트로 대체해주세요
            supportFragmentManager.beginTransaction()
                .replace(R.id.fl, homeFragment) // R.id.fragment_container 대신에 R.id.fl로 변경해주세요
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()
        }
        // 전문가 서비스를 구독했는지 안했는지 파악합니다.
        // TODO : @김국현 DB 매칭 확인
        val matching: Boolean = true

        if (matching) {
            bnv.menu.clear()  // 기존 메뉴 삭제
            bnv.inflateMenu(R.menu.menu_item2)  // menu_item.xml 메뉴 적용
        } else {
            bnv.menu.clear()  // 기존 메뉴 삭제
            bnv.inflateMenu(R.menu.menu_item)  // menu_item2.xml 메뉴 적용
        }
        supportFragmentManager.beginTransaction().replace(
            R.id.fl,
            HomeFragment()
        ).commit()


        bnv.setOnItemSelectedListener {
            when(it.itemId){

                R.id.tab1->{ // 메인 페이지
                        supportFragmentManager.beginTransaction().replace(
                            R.id.fl,
                            HomeFragment()
                        ).commit()
                }
                R.id.tab2->{ // 기기관리
                        supportFragmentManager.beginTransaction().replace(
                            R.id.fl,
                            DeviceFragment()
                        ).commit()
                }
                R.id.tab3->{ // 전문가 페이지
//                        supportFragmentManager.beginTransaction().replace(
//                            R.id.fl,
//                            ExpertFragment()
//                        ).commit()
//                    // do
                }
                R.id.tab4->{ // 마이 페이지
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

