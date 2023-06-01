package com.example.itsmungapplication.Fragment

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.itsmungapplication.DevicewifiActivity
import com.example.itsmungapplication.R

class DeviceFragment : Fragment() {

    private lateinit var wifiManager: WifiManager
    private lateinit var wifiScanReceiver: BroadcastReceiver

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        wifiManager = requireContext().applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        wifiScanReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                val success = intent.getBooleanExtra(WifiManager.EXTRA_RESULTS_UPDATED, false)
                if (success) {
                    scanSuccess()
                } else {
                    scanFailure()
                }
            }
        }
    }

    fun onClickWifiScan() {
        val success = wifiManager.startScan()
        if (!success) {
            Toast.makeText(requireContext(), "Wi-Fi Scan에 실패하였습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun scanSuccess() {
//        val results = wifiManager.scanResults
        // TODO: Wi-Fi 스캔 결과 사용
    }

    private fun scanFailure() {
        // TODO: Wi-Fi 스캔 실패 처리
        // 이전 스캔 결과 사용 가능: val results = wifiManager.scanResults
    }

    override fun onResume() {
        super.onResume()
        val intentFilter = IntentFilter()
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)
        requireContext().registerReceiver(wifiScanReceiver, intentFilter)
    }

    override fun onPause() {
        super.onPause()
        requireContext().unregisterReceiver(wifiScanReceiver)
    }
}
