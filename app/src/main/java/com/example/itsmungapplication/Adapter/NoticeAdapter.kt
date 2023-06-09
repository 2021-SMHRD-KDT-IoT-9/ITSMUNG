package com.example.itsmungapplication.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.itsmungapplication.NoticeVO
import com.example.itsmungapplication.R

class NoticeAdapter (context: Context, layout: Int, chatlist: MutableList<NoticeVO>) :
    RecyclerView.Adapter<NoticeAdapter.ViewHolder>() {

    val context = context
    val layout = layout
    val chatlist = chatlist


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var img_notice: ImageView = itemView.findViewById(R.id.img_notice)
        var tv_notice_title: TextView = itemView.findViewById(R.id.tv_notice_title)
        var tv_notice_content: TextView = itemView.findViewById(R.id.tv_notice_content)
    }

    // 한 칸에 들어갈 디자인
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): NoticeAdapter.ViewHolder {
        val cardview = LayoutInflater.from(context)
            .inflate(R.layout.card_layout_notice, parent, false)
        return ViewHolder(cardview)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.img_notice.setImageResource(chatlist[position].imageResId)
        holder.tv_notice_title.text= chatlist[position].title
        holder.tv_notice_content.text = chatlist[position].content
    }


    override fun getItemCount(): Int {
        return chatlist.size
    }

}