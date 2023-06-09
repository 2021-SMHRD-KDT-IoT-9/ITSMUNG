package com.example.itsmungapplication.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.itsmungapplication.R

class ExpertfragAdapter(private val messageList: List<String>) :
    RecyclerView.Adapter<ExpertfragAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvChatMessage: TextView = itemView.findViewById(R.id.tv_chat_message)
        val tvChatDate: TextView = itemView.findViewById(R.id.tv_chat_date)
        val tvChatIsShown: TextView = itemView.findViewById(R.id.tv_chat_isShown)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.expert_chat_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val message = messageList[position]
        holder.tvChatMessage.text = message
        // Set other values here if needed
    }

    override fun getItemCount(): Int {
        return messageList.size
    }
}