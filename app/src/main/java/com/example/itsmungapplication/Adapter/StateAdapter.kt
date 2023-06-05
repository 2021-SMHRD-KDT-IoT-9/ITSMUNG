package com.example.itsmungapplication.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.itsmungapplication.R

class StateAdapter:
    RecyclerView.Adapter<StateAdapter.ViewHolder>(){
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var itemimage: ImageView = itemView.findViewById(R.id.img_state)
        var itemtitle: TextView = itemView.findViewById(R.id.tv_state_title)
        var itemdetail: TextView = itemView.findViewById(R.id.tv_state_content)
    }

    // 한 칸에 들어갈 디자인을 infalte작업을 한 후에 ViewHolder클래스로
    // infalte가 된 view를 넘겨주는 일을 한다.
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): StateAdapter.ViewHolder {
        val cardView = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_layout_state, parent, false)

        return ViewHolder(cardView)
    }

    override fun onBindViewHolder(holder: StateAdapter.ViewHolder, position: Int) {
        holder.itemimage.setImageResource(R.drawable.pawprint)
        holder.itemtitle.setText("대변에 이상이 보입니다.")
        holder.itemdetail.setText(" 딱딱한 토끼 똥 형태 : 변비 \n 빨간색 변 : 항문 질병 및 위장 출혈의 의심됩니다. \n 병원 내원을 추천합니다.")
    }

    override fun getItemCount(): Int {
        return 5
    }
}