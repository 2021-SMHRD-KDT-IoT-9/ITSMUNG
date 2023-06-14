package com.example.itsmungapplication.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("user/login")
    fun login(@Body request: LoginRequest) : Call<LoginResponse>

    @POST("user/join")
    fun join(@Body request: JoinRequest) : Call<JoinResponse>

    @POST("user/info")
    fun userInfo(@Body request: UserInfoRequest) : Call<UserInfoResponse>

    @POST("user/update")
    fun userUpdate(@Body request: UserUpdateRequest) : Call<UserUpdateResponse>

    @POST("dog/info")
    fun dogInfo(@Body request: DogInfoRequest) : Call<DogInfoResponse>

    @POST("dog/join")
    fun dogJoin(@Body request: DogJoinRequest) : Call<DogJoinResponse>

    @POST("dog/update")
    fun dogUpdate(@Body request: DogUpdateRequest) : Call<DogUpdateResponse>

    @POST("match/userId")
    fun match(@Body request: MatchingRequest) : Call<MatchingResponse>

}