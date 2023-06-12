package com.example.itsmungapplication.api

import com.example.itsmungapplication.vo.DogVO
import com.example.itsmungapplication.vo.UserVO

// User Login
data class LoginRequest(
    val userId : String,
    val userPw : String
)

data class LoginResponse(
    val token : String,
    val userId : String,
    val userPw : String
)

// User Join
data class JoinRequest(
    val user: UserVO
)

data class JoinResponse(
    val message : String
)

// Dog Info
data class DogRequest(
    val userId : String
)

data class DogResponse(
    val dog : DogVO
)

// Dog Join
data class DogJoinRequest(
    val dog : DogVO
)

data class DogJoinResponse(
    val message : String
)

// Matching
data class MatchingRequest(
    val userId : String
)

data class MatchingResponse(
 val match : String
)