package com.example.itsmungapplication

import android.content.Context
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

        // 내부에 저장된 doctor_id(DB에서 가져오기용)
        val sharedPreferences = getSharedPreferences("my_app", Context.MODE_PRIVATE)
        val doctorId = sharedPreferences.getString("doctor_id", null)


        val bnv: BottomNavigationView = findViewById(R.id.bnv)
        val fl: FrameLayout = findViewById(R.id.fl)

        // 첫화면(실행시) 보이게
        supportFragmentManager.beginTransaction().replace(
            R.id.fl,
            DoctorMainFragment()
        ).commit()

        bnv.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.tab1 -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fl,
                        DoctorMainFragment()
                    ).commit()
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

            true
        }
    }
}