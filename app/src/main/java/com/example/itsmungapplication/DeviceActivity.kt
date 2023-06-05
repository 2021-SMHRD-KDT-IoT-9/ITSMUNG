package com.example.itsmungapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Button

class DeviceActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_device)
//
//        val btn_device : Button = findViewById(R.id.btn_device)
//
//        btn_device.setOnClickListener {
//            val intent = Intent(this@DeviceActivity,
//                DevicewifiActivity::class.java)
//            startActivity(intent)
//        }
//    }

    private lateinit var btn_Device: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_device)

        btn_Device = findViewById(R.id.btn_device)
        btn_Device.setOnClickListener {
            // Wi-Fi 연결 설정 대화상자 열기
            openWifiSettingsDialog()
        }
    }

    private fun openWifiSettingsDialog() {
        val wifiSettingsIntent = Intent(Settings.Panel.ACTION_INTERNET_CONNECTIVITY)
        startActivity(wifiSettingsIntent)
    }


}