package com.example.itsmungapplication.api

import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiManager {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://m.itsmung.store/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val apiService: ApiService = retrofit.create(ApiService::class.java)

    // 로그인 통신
    fun login(request: LoginRequest, callback: (LoginResponse?) -> Unit)
    {
        val call = apiService.login(request)

        call.enqueue(object : Callback<LoginResponse>
        {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>)
            {
                if(response.isSuccessful)
                {
                    val response = response.body()
                    callback(response)
                }
                else
                {
                    callback(null)
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable)
            {
                callback(null)
            }
        })
    }

    // 회원가입 통신
    fun join(request: JoinRequest, callback: (JoinResponse?) -> Unit)
    {
        val call = apiService.join(request)

        call.enqueue(object : Callback<JoinResponse>
        {
            override fun onResponse(call: Call<JoinResponse>, response: Response<JoinResponse>)
            {
                if(response.isSuccessful)
                {
                    val response = response.body()
                    callback(response)
                }
                else
                {
                    callback(null)
                }
            }

            override fun onFailure(call: Call<JoinResponse>, t: Throwable)
            {
                callback(null)
            }
        })
    }

    // 개 정보 불러오기 통신
    fun dogInfo(request: DogRequest, callback: (DogResponse?) -> Unit)
    {
        val call = apiService.dogInfo(request)

        call.enqueue(object : Callback<DogResponse>
        {
            override fun onResponse(call: Call<DogResponse>, response: Response<DogResponse>)
            {
                if(response.isSuccessful)
                {
                    val response = response.body()
                    callback(response)
                }
                else
                {
                    callback(null)
                }
            }

            override fun onFailure(call: Call<DogResponse>, t: Throwable)
            {
                callback(null)
            }

        })

    }

    fun dogJoin(request: DogJoinRequest, callback: (DogJoinResponse?) -> Unit)
    {
        val call = apiService.dogJoin(request)
    }
}