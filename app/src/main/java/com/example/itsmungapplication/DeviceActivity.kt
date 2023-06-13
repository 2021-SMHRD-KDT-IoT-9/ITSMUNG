package com.example.itsmungapplication
//
import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.wifi.ScanResult
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class DeviceActivity : AppCompatActivity() {
//
//    private val WIFI_PERMISSION_REQUEST_CODE = 1001
//
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_device)
//
//        val btn_device: Button = findViewById(R.id.btn_device)
//
//        btn_device.setOnClickListener {
//            // 와이파이 권한이 있는지 확인
//            if (checkWifiPermission()) {
//                // 와이파이 정보 가져오기
//                getWifiInfo()
//            } else {
//                // 와이파이 권한 요청
//                requestWifiPermission()
//            }
        }
    }
//
//    private fun checkWifiPermission(): Boolean {
//        val permission = Manifest.permission.ACCESS_FINE_LOCATION
//        val result = ContextCompat.checkSelfPermission(this, permission)
//        return result == PackageManager.PERMISSION_GRANTED
//    }
//
//    private fun requestWifiPermission() {
//        val permission = Manifest.permission.ACCESS_FINE_LOCATION
//        ActivityCompat.requestPermissions(this, arrayOf(permission), WIFI_PERMISSION_REQUEST_CODE)
//    }
//
//    private fun getWifiInfo() {
//        val wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
//
//        // 와이파이가 비활성화된 경우 활성화 요청
//        if (!wifiManager.isWifiEnabled) {
//            wifiManager.isWifiEnabled = true
//        }
//
//        // 와이파이 스캔
////        val scanResults = wifiManager.scanResults
//
//        if (scanResults.isNotEmpty()) {
//            // 가장 강력한 와이파이 정보 가져오기
//            val strongestWifi = scanResults.maxByOrNull { it.level }
//            val wifiInfo = strongestWifi?.SSID ?: "Unknown"
//
//            // 현재 연결 가능한 와이파이 정보 가져오기
//            val connectionInfo: WifiInfo? = wifiManager.connectionInfo
//            val connectedWifiInfo = connectionInfo?.ssid ?: "Not connected"
//
//            val message = "Strongest Wi-Fi: $wifiInfo\nConnected Wi-Fi: $connectedWifiInfo"
//            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
//        } else {
//            Toast.makeText(this, "No Wi-Fi found", Toast.LENGTH_SHORT).show()
//        }
//    }
//
//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if (requestCode == WIFI_PERMISSION_REQUEST_CODE) {
//            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                // 와이파이 권한 허용된 경우 와이파이 정보 가져오기
//                getWifiInfo()
//            } else {
//                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//}
