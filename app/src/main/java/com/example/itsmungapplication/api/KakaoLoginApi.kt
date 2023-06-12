package com.example.itsmungapplication.api

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class KakaoLoginApi: Application()  {

        override fun onCreate() {
            super.onCreate()
            // 다른 초기화 코드들

            // Kakao SDK 초기화
            KakaoSdk.init(this, "40891bb2be733a2e8fbfc5574e83a9c9")
        }

}