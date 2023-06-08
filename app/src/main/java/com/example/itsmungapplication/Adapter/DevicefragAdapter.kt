package com.example.itsmungapplication.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.itsmungapplication.R

class DevicefragAdapter :
    RecyclerView.Adapter<DevicefragAdapter.ViewHolder>(){
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var img_devicef: ImageView = itemView.findViewById(R.id.img_devicef)
        var tv_devicef_title: TextView = itemView.findViewById(R.id.tv_devicef_title)
        var tv_devicef_content: TextView = itemView.findViewById(R.id.tv_devicef_content)
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): DevicefragAdapter.ViewHolder {
        val cardView = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_layout_devicef, parent, false)

        return DevicefragAdapter.ViewHolder(cardView)
    }

    override fun onBindViewHolder(holder: DevicefragAdapter.ViewHolder, position: Int) {
        holder.img_devicef.setImageResource(R.drawable.check)
        holder.tv_devicef_title.setText(" 물 수위가 낮습니다. ")
        holder.tv_devicef_content.setText(" 오수통을 비우고 물을 채워주세요. \n ")
    }


    override fun getItemCount(): Int {
        return 5
    }
}