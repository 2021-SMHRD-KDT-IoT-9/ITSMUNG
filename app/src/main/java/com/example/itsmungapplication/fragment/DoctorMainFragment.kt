package com.example.itsmungapplication.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.itsmungapplication.DoctorDogDetailActivity
import com.example.itsmungapplication.R


class DoctorMainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?



    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_doctor_main, container, false)

        val btn_doctor_main : Button = view.findViewById(R.id.btn_doctor_main1)

        // 버튼 클릭 이벤트 처리
        btn_doctor_main.setOnClickListener {
            // DoctorDogDetailActivity로 이동
            val intent = Intent(activity, DoctorDogDetailActivity::class.java)
            startActivity(intent)
        }

        return view
    }

}