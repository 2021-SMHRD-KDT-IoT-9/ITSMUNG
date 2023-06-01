package com.example.itsmungapplication

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
//                R.id.tab3->{ // 전문가 페이지
//                        supportFragmentManager.beginTransaction().replace(
//                            R.id.fl,
//                            ExpertFragment()
//                        ).commit()
//                    // do
//                }
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

