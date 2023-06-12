package com.example.itsmungapplication.fragment

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.itsmungapplication.CctvActivity
import com.example.itsmungapplication.DogJoinActivity
import com.example.itsmungapplication.DogPeaActivity
import com.example.itsmungapplication.DogStoolActivity
import com.example.itsmungapplication.PayActivity
import com.example.itsmungapplication.R
import com.example.itsmungapplication.WeightActivity
import com.example.itsmungapplication.api.ApiManager
import com.example.itsmungapplication.api.DogInfoRequest
import java.time.LocalDate


class HomeFragment : Fragment() {

    private lateinit var btn_home_dogstool: Button
    private lateinit var btn_home_dogpea: Button
    private lateinit var btn_home_cctv: Button
    private lateinit var btn_home_weight: Button
    private lateinit var imgbtn_home_banner : ImageButton
    private lateinit var btn_home_doginfochange : Button
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // 내부에 저장된 user_id(DB에서 가져오기용)
        val sharedPreferences = requireContext().getSharedPreferences("my_app", Context.MODE_PRIVATE)
        val userId = sharedPreferences.getString("user_id", null)

        btn_home_dogstool = view.findViewById(R.id.btn_home_dogstool)
        btn_home_dogpea = view.findViewById(R.id.btn_home_dogpea)
        btn_home_cctv = view.findViewById(R.id.btn_home_cctv)
        btn_home_weight = view.findViewById(R.id.btn_home_weight)
        imgbtn_home_banner = view.findViewById(R.id.imgbtn_home_banner)
        btn_home_doginfochange = view.findViewById(R.id.btn_home_doginfochange)

        // 개 정보 불러오기
        val request = DogInfoRequest(userId)

        ApiManager.dogInfo(request)
        {
            response->
            if(response != null)
            {

            }
            else
            {

            }
        }


        // TODO : 회원id를 토대로 반려견의 정보를 가져와서 입력한다. 아래의 변수의 데이터를 입력해주세요
        //        소다\n 나이 : 3세\n 성별 : 여아\n 견종 : 하이브리드종 \n           중성화 X
        var dog_name : String = ""
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

        var dog_gender : String = ""
        var dog_species : String = ""
        // 견종
        var dog_neutered : String = ""
        btn_home_doginfochange.setText(" 이름 : $dog_name\n 나이 : $dog_age 세\n 성별 : $dog_gender \n 견종 : $dog_species\n 중성화 : $dog_neutered")


        btn_home_dogstool.setOnClickListener {
            val intent = Intent(activity, DogStoolActivity::class.java)
            startActivity(intent)
        }

        btn_home_dogpea.setOnClickListener {
            val intent = Intent(activity, DogPeaActivity::class.java)
            startActivity(intent)
        }

        btn_home_cctv.setOnClickListener {
            val intent = Intent(activity, CctvActivity::class.java)
            startActivity(intent)
        }

        btn_home_weight.setOnClickListener {
            val intent = Intent(activity, WeightActivity::class.java)
            startActivity(intent)
        }

        imgbtn_home_banner.setOnClickListener {
            val intent = Intent(activity, PayActivity::class.java)
            startActivity(intent)
        }

        btn_home_doginfochange.setOnClickListener {
            val intent = Intent(activity, DogJoinActivity::class.java)
            // 이동할 때 반려견의 기본 정보를 같이 가지고 이동합니다.
            intent.putExtra("dog_name", dog_name)
            intent.putExtra("dog_age", dog_age)
            intent.putExtra("dog_gender", dog_gender)
            intent.putExtra("dog_species", dog_species)
            intent.putExtra("dog_neutered", dog_neutered)
            startActivity(intent)
        }




        return view

    }
}


