package com.example.itsmungapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class DeviceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_device)

        val btn_device : Button = findViewById(R.id.btn_device)

        btn_device.setOnClickListener {
            val intent = Intent(this@DeviceActivity,
                MainActivity::class.java)
            startActivity(intent)
        }
    }
}