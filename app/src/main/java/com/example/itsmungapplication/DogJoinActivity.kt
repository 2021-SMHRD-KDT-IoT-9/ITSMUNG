package com.example.itsmungapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class DogJoinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dog_join)

        val btn_dog_join_save : Button = findViewById(R.id.btn_dog_join_save)

        btn_dog_join_save.setOnClickListener {
            val intent = Intent(this@DogJoinActivity,
                MainActivity::class.java)
            startActivity(intent)
        }
    }
}