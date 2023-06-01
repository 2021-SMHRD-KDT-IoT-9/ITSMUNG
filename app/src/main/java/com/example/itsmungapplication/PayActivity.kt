package com.example.itsmungapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class PayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay)

        val btn_pay : Button = findViewById(R.id.btn_pay)

        btn_pay.setOnClickListener {
            val intent = Intent(this@PayActivity,
                PayButtonActivity::class.java)
            startActivity(intent)
        }
    }
}