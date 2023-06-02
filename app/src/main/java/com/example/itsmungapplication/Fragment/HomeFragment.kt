package com.example.itsmungapplication.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.example.itsmungapplication.CctvActivity
import com.example.itsmungapplication.DogJoinActivity
import com.example.itsmungapplication.DogPeaActivity
import com.example.itsmungapplication.DogStoolActivity
import com.example.itsmungapplication.PayActivity
import com.example.itsmungapplication.R
import com.example.itsmungapplication.WeightActivity


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val btn_home_dogstool: Button = view.findViewById(R.id.btn_home_dogstool)
        val btn_home_dogpea: Button = view.findViewById(R.id.btn_home_dogpea)
        val btn_home_cctv: Button = view.findViewById(R.id.btn_home_cctv)
        val btn_home_weight : Button = view.findViewById(R.id.btn_home_weight)
        val imgbtn_home_banner : ImageButton = view.findViewById(R.id.imgbtn_home_banner)
        val btn_home_doginfochange : Button = view.findViewById(R.id.btn_home_doginfochange)

        // TODO : 회원id를 토대로 반려견의 정보를 가져와서 입력한다. 아래의 변수의 데이터를 입력해주세요
//        소다\n 나이 : 3세\n 성별 : 여아\n 견종 : 하이브리드종 \n           중성화 X
        var dog_name : String = ""
        var dog_age : Int = 0
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
