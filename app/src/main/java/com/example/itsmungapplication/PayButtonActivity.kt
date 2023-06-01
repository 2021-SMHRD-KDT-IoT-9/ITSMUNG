package com.example.itsmungapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class PayButtonActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay_button)

        val btn_pay_button : Button = findViewById(R.id.btn_pay_button)

        btn_pay_button.setOnClickListener {
            val intent = Intent(this@PayButtonActivity,
                PayCompleteActivity::class.java)
            startActivity(intent)
        }


        
    }
}