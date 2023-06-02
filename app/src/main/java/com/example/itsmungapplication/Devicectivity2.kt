package com.example.itsmungapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Devicectivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_devicectivity2)

        val btn_device : Button = findViewById(R.id.btn_device)

        btn_device.setOnClickListener {
            val intent = Intent(this@Devicectivity2,
                DevicewifiActivity::class.java)
            startActivity(intent)
        }
    }
}