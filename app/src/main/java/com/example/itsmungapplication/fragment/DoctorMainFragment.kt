package com.example.itsmungapplication.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.itsmungapplication.DoctorDogDetailActivity
import com.example.itsmungapplication.R
import java.time.LocalDate


class DoctorMainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?



    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_doctor_main, container, false)


        // 내부에 저장된 user_id(DB에서 가져오기용)
        val sharedPreferences = requireContext().getSharedPreferences("my_app", Context.MODE_PRIVATE)
        val doctorId = sharedPreferences.getString("doctor_id", null)

        val btn_doctor_main : Button = view.findViewById(R.id.btn_doctor_main1)

        // TODO : 회원id를 토대로 반려견의 정보를 가져와서 입력한다. 아래의 변수의 데이터를 입력해주세요
//        소다\n 나이 : 3세\n 성별 : 여아\n 견종 : 하이브리드종 \n           중성화 X
        var dog_name : String = ""
        var dog_gender : String = ""
        var dog_age : Int = 0
        var birthday : String = "2020-05-07"
        if (birthday.isNotEmpty()) {
            val parts = birthday.split("-") // 년, 월, 일로 분리합니다.
            if (parts.size == 3) {
                val year = parts[0].toInt()
                val month = parts[1].toInt()
                val day = parts[2].toInt()
                val today = LocalDate.now() // 현재 날짜를 가져옵니다.
                if(dog_age != null || dog_age != 0){
                    dog_age = today.year - year - if (today.monthValue < month || (today.monthValue == month && today.dayOfMonth < day)) 1 else 0
                }
            }
        }
        // 견종
        var dog_species : String = ""
        // 중성화
        var dog_neutered : String = ""



        btn_doctor_main.setOnClickListener {
            // DoctorDogDetailActivity로 이동
            val intent = Intent(activity, DoctorDogDetailActivity::class.java)
            // 이동할 때 반려견의 기본 정보를 같이 가지고 이동합니다.
            intent.putExtra("dog_name", dog_name)
            intent.putExtra("dog_gender", dog_gender)
            intent.putExtra("dog_age", dog_age)
            intent.putExtra("dog_species", dog_species)
            intent.putExtra("dog_neutered", dog_neutered)
            startActivity(intent)
            startActivity(intent)
        }

        return view
    }

}