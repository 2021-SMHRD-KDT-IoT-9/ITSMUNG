package com.example.itsmungapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DeviceAdapter(private val wifiList: List<DeviceVO>) : RecyclerView.Adapter<DeviceAdapter.DeviceViewHolder>() {

    class DeviceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvWifiName: TextView = itemView.findViewById(R.id.tvWifiName)

        fun bind(device: DeviceVO) {
            tvWifiName.text = device.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.wifi_list, parent, false)
        return DeviceViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        val device = wifiList[position]
        holder.bind(device)
    }

    override fun getItemCount(): Int {
        return wifiList.size
    }
}