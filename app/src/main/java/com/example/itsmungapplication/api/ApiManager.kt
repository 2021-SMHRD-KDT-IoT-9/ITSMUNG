package com.example.itsmungapplication.api

import android.util.Log
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

    // 유저 정보 불러오기 통신
    fun userInfo(request: UserInfoRequest, callback: (UserInfoResponse?) -> Unit)
    {
        val call = apiService.userInfo(request)

        call.enqueue(object : Callback<UserInfoResponse>
        {
            override fun onResponse(call: Call<UserInfoResponse>, response: Response<UserInfoResponse>)
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

            override fun onFailure(call: Call<UserInfoResponse>, t: Throwable)
            {
                callback(null)
            }
        })
    }

    // 유저 정보 변경하기 통신
    fun userUpdate(request: UserUpdateRequest, callback: (UserUpdateResponse?) -> Unit)
    {
        val call = apiService.userUpdate(request)

        call.enqueue(object : Callback<UserUpdateResponse>
        {
            override fun onResponse(call: Call<UserUpdateResponse>, response: Response<UserUpdateResponse>)
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

            override fun onFailure(call: Call<UserUpdateResponse>, t: Throwable)
            {
                callback(null)
            }
        })
    }

    // 개 정보 불러오기 통신
    fun dogInfo(request: DogInfoRequest, callback: (DogInfoResponse?) -> Unit)
    {
        val call = apiService.dogInfo(request)

        call.enqueue(object : Callback<DogInfoResponse>
        {
            override fun onResponse(call: Call<DogInfoResponse>, response: Response<DogInfoResponse>)
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

            override fun onFailure(call: Call<DogInfoResponse>, t: Throwable)
            {
                callback(null)
            }

        })

    }

    fun dogUpdate(request: DogUpdateRequest, callback: (DogUpdateResponse?) -> Unit)
    {
        val call = apiService.dogUpdate(request)

        call.enqueue(object : Callback<DogUpdateResponse>
        {
            override fun onResponse(call: Call<DogUpdateResponse>, response: Response<DogUpdateResponse>)
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

            override fun onFailure(call: Call<DogUpdateResponse>, t: Throwable)
            {
                callback(null)
            }
        })
    }

    fun dogJoin(request: DogJoinRequest, callback: (DogJoinResponse?) -> Unit)
    {
        val call = apiService.dogJoin(request)

        call.enqueue(object : Callback<DogJoinResponse>
        {
            override fun onResponse(call: Call<DogJoinResponse>, response: Response<DogJoinResponse>)
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

            override fun onFailure(call: Call<DogJoinResponse>, t: Throwable)
            {
                callback(null)
            }
        })


    }

    fun match(request: MatchingRequest, callback: (MatchingResponse?) -> Unit)
    {
        val call = apiService.match(request)

        call.enqueue(object : Callback<MatchingResponse>
        {
            override fun onResponse(call: Call<MatchingResponse>, response: Response<MatchingResponse>)
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

            override fun onFailure(call: Call<MatchingResponse>, t: Throwable)
            {
                callback(null)
            }
        })
    }
}