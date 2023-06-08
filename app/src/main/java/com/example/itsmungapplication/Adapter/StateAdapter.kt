package com.example.itsmungapplication.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.itsmungapplication.AlarmVO
import com.example.itsmungapplication.R

class StateAdapter(context:Context, layout: Int, alram:MutableList<AlarmVO> ):
    RecyclerView.Adapter<StateAdapter.ViewHolder>(){

    val context = context
    val layout = layout
    val alram = alram

    val inflater = LayoutInflater.from(context)

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var img_state: ImageView = itemView.findViewById(R.id.img_state)
        var tv_state_title: TextView = itemView.findViewById(R.id.tv_state_title)
        var tv_state_content: TextView = itemView.findViewById(R.id.tv_state_content)
    }

    // 한 칸에 들어갈 디자인
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): StateAdapter.ViewHolder {
        val cardView = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_layout_state, parent, false)

        return ViewHolder(cardView)
    }

    override fun onBindViewHolder(holder: StateAdapter.ViewHolder, position: Int) {
        holder.img_state.setImageResource(R.drawable.pawprint)
        holder.tv_state_title.setText("대변에 이상이 보입니다.")
        holder.tv_state_content.setText(" 딱딱한 토끼 똥 형태 : 변비 \n 빨간색 변 : 항문 질병 및 위장 출혈의 의심됩니다. \n 병원 내원을 추천합니다.")
    }

    override fun getItemCount(): Int {
        return 5
    }
}