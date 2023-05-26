package com.example.itsmungapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import com.example.itsmungapplication.Fragment.DeviceFragment
import com.example.itsmungapplication.Fragment.MainFragment
import com.example.itsmungapplication.Fragment.MypageFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bnv : BottomNavigationView = findViewById(R.id.bnv)
        val fl : FrameLayout = findViewById(R.id.fl)

        supportFragmentManager.beginTransaction().replace(
            R.id.fl,
            MainFragment()
        ).commit()

        bnv.setOnItemSelectedListener {
            when(it.itemId){
                R.id.tab1->{ // 기기관리
                        supportFragmentManager.beginTransaction().replace(
                            R.id.fl,
                            DeviceFragment()
                        ).commit()
                }
                R.id.tab2->{ // 메인 페이지
                        supportFragmentManager.beginTransaction().replace(
                            R.id.fl,
                            MainFragment()
                        ).commit()
                }
                R.id.tab3->{ // 마이 페이지
                        supportFragmentManager.beginTransaction().replace(
                            R.id.fl,
                            MypageFragment()
                        ).commit()

                    //mas
                }

           }
                true
        }

    }


}

