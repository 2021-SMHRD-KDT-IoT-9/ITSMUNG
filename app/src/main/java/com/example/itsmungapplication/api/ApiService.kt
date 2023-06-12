package com.example.itsmungapplication.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("user/login")
    fun login(@Body request: LoginRequest) : Call<LoginResponse>

    @POST("user/join")
    fun join(@Body request: JoinRequest) : Call<JoinResponse>

    @POST("dog/info")
    fun dogInfo(@Body request: DogInfoRequest) : Call<DogInfoResponse>

    @POST("dog/join")
    fun dogJoin(@Body request: DogJoinRequest) : Call<DogJoinResponse>

    @POST("match/userId")
    fun isMatch(@Body request: MatchingRequest) : Call<MatchingResponse>
}