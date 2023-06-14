package com.example.itsmungapplication.api

import com.example.itsmungapplication.vo.DogVO
import com.example.itsmungapplication.vo.UserVO

// User Login
data class LoginRequest(
    val user : UserVO
)

data class LoginResponse(
    val token : String,
    val userId : String,
)

// User Info
data class UserInfoRequest(
    val user : UserVO
)

data class UserInfoResponse(
    val user : UserVO
)

// User Join
data class JoinRequest(
    val user: UserVO
)

data class JoinResponse(
    val message : String
)

// User Update
data class UserUpdateRequest(
    val user: UserVO
)

data class UserUpdateResponse(
    val message: String
)
// Dog Info
data class DogInfoRequest(
    val user : UserVO
)

data class DogInfoResponse(
    val dog : DogVO
)

// Dog Join
data class DogJoinRequest(
    val dog : DogVO,
    val userId : String?
)

data class DogJoinResponse(
    val message : String?
)

// Dog Update
data class DogUpdateRequest(
    val dog : DogVO,
    val userId : String?
)

data class DogUpdateResponse(
    val message : String?
)
// User Match
data class MatchingRequest(
    val userId : String?
)

data class MatchingResponse(
 val match : Boolean
)