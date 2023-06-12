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
import com.example.itsmungapplication.vo.UserVO
import java.time.LocalDate


class HomeFragment : Fragment() {

    private lateinit var btn_home_dogstool: Button
    private lateinit var btn_home_dogpea: Button
    private lateinit var btn_home_cctv: Button
    private lateinit var btn_home_weight: Button
    private lateinit var imgbtn_home_banner : ImageButton
    private lateinit var btn_home_doginfochange : Button

    private lateinit var dog_name : String
    private lateinit var dog_age : Number
    private lateinit var dog_gender : String
    private lateinit var dog_species : String
    private lateinit var dog_neutered : String


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // 내부에 저장된 userId 불러오기
        val sharedPreferences = requireContext().getSharedPreferences("my_app", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("token", null)
        val userId = sharedPreferences.getString("userId", null)

        btn_home_dogstool = view.findViewById(R.id.btn_home_dogstool)
        btn_home_dogpea = view.findViewById(R.id.btn_home_dogpea)
        btn_home_cctv = view.findViewById(R.id.btn_home_cctv)
        btn_home_weight = view.findViewById(R.id.btn_home_weight)
        imgbtn_home_banner = view.findViewById(R.id.imgbtn_home_banner)
        btn_home_doginfochange = view.findViewById(R.id.btn_home_doginfochange)

        val user = UserVO()
        user.userId = userId
        // 애견 정보 불러오기
        val request = DogInfoRequest(user)

        ApiManager.dogInfo(request)
        {

            response->
            // 애견이 등록되어 있을 경우
            if(response != null)
            {
                val dog = response.dog
                dog_name = dog.dogName
                if (dog.dogBirth.isNotEmpty()) {
                    val parts = dog.dogBirth.split("-") // 년, 월, 일로 분리합니다.
                    if (parts.size == 3) {
                        val year = parts[0].toInt()
                        val month = parts[1].toInt()
                        val day = parts[2].toInt()
                        val today = LocalDate.now() // 현재 날짜를 가져옵니다.
                        if(dog_age != null || dog_age != 0)
                        {
                            dog_age = today.year - year - if (today.monthValue < month || (today.monthValue == month && today.dayOfMonth < day)) 1 else 0
                        }
                    }
                }
                dog_gender = dog.dogGender
                dog_species = dog.dogBreed
                dog_neutered = dog.dogNeutered
            }
            // 애견이 등록되지 않은 경우
            else
            {
                dog_name = ""
                dog_age = 0
                dog_gender = ""
                dog_species = ""
                dog_neutered = ""
            }
        }


        // TODO : 회원id를 토대로 반려견의 정보를 가져와서 입력한다. 아래의 변수의 데이터를 입력해주세요
        //        소다\n 나이 : 3세\n 성별 : 여아\n 견종 : 하이브리드종 \n           중성화 X
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


