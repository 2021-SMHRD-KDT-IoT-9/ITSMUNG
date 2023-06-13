package com.example.itsmungapplication.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.itsmungapplication.R

class DevicefragAdapter(private val messageList: List<String>) :
    RecyclerView.Adapter<DevicefragAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvChatMessage: TextView = itemView.findViewById(R.id.tv_chat_message)
        val tvChatDate: TextView = itemView.findViewById(R.id.tv_chat_date)
        val tvChatIsShown: TextView = itemView.findViewById(R.id.tv_chat_isShown)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutResId = when (viewType) {
            VIEW_TYPE_MINE -> R.layout.expert_chat_list_mine
            VIEW_TYPE_OTHERS -> R.layout.expert_chat_list_others
            else -> throw IllegalArgumentException("Invalid view type")
        }

        val view = LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)
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

    override fun getItemViewType(position: Int): Int {
        // 짝수 인덱스는 VIEW_TYPE_MINE, 홀수 인덱스는 VIEW_TYPE_OTHERS로 설정합니다.
        return if (position % 2 == 0) {
            VIEW_TYPE_MINE
        } else {
            VIEW_TYPE_OTHERS
        }
    }

    companion object {
        private const val VIEW_TYPE_MINE = 0
        private const val VIEW_TYPE_OTHERS = 1
    }
}