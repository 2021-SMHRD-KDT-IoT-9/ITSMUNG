package com.example.itsmungapplication.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.itsmungapplication.R

class NoticeAdapter () :
    RecyclerView.Adapter<NoticeAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var itemimage: ImageView = itemView.findViewById(R.id.img_notice)
        var itemtitle: TextView = itemView.findViewById(R.id.tv_notice_title)
        var itemdetail: TextView = itemView.findViewById(R.id.tv_notice_content)
    }

    // 한 칸에 들어갈 디자인을 infalte작업을 한 후에 ViewHolder클래스로
    // infalte가 된 view를 넘겨주는 일을 한다.
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): NoticeAdapter.ViewHolder {
        val cardView = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_layout_notice, parent, false)

        return ViewHolder(cardView)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemimage.setImageResource(R.drawable.check)
        holder.itemtitle.setText("시스템 정기 점검 안내")
        holder.itemdetail.setText(" 금주 주말에 시스템 정기 정검이 있습니다. \n 작업 시간 동안 시스템 접속이 원활하지 않을 수 있습니다.")
    }


    override fun getItemCount(): Int {
        return 5
    }

}