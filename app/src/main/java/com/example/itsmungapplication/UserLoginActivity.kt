package com.example.itsmungapplication

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.viewModels
import android.content.ContentValues.TAG
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.pm.Signature
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class UserLoginActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: Editor

    // TAG for kakaoLogin - kakaoTalk가 없는 경우
    private val mCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        // TODO : DB체크를 통해 해당하는 아이디가 있는지 확인합니다.
        //  없는 경우 UserJoinActivity로 이동합니다.
        //  있는 경우 MainActivity로 들어갑니다.
        if (error != null) {
            Log.e(ContentValues.TAG, "로그인 실패 $error")
            Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
            // 왜 계속 오류가 뜰까?
            // hash 값 잘못 등록 오ㅗ류 -> 해결

        } else if (token != null) {
            Log.e(ContentValues.TAG, "로그인 성공 ${token.accessToken}")
            UserApiClient.instance.me { user, error ->
                if (error != null) {
                    Log.e(TAG, "사용자 정보 요청 실패", error)
                    Toast.makeText(this, "사용자 정보 요청 실패", Toast.LENGTH_SHORT).show()
                }
                else if (user != null) {
                    var scopes = mutableListOf<String>()

                    if (user.kakaoAccount?.emailNeedsAgreement == true) { scopes.add("account_email") }
                    //scope 목록을 전달하여 카카오 로그인 요청
                    UserApiClient.instance.loginWithNewScopes(this, scopes) { token, error ->
                        if (error != null) {
                            Log.e(TAG, "사용자 추가 동의 실패", error)
                            Toast.makeText(this, "사용자 추가 동의 실패", Toast.LENGTH_SHORT).show()
                        } else {
                            Log.d(TAG, "allowed scopes: ${token!!.scopes}")

                            // 사용자 정보 재요청
                            UserApiClient.instance.me { user, error ->
                                if (error != null) {
                                    Log.e(TAG, "사용자 정보 요청 실패", error)
                                    Toast.makeText(this, "사용자 정보 요청 실패", Toast.LENGTH_SHORT).show()
                                }
                                else if (user != null) {
                                    Log.i(TAG, "사용자 정보 요청 성공" +
                                            "\n회원번호: ${user.id}" +
                                            "\n이메일: ${user.kakaoAccount?.email}")
                                    // TODO : DB 체크를 필요합니다.
                                    //  카카오 등록이 된 이메일이 있는지 확인합니다.
                                    var DBKakaoCheck : Boolean = true
                                    if(DBKakaoCheck){
                                        startMainActivity()
                                        editor.putString("user_id", "${user.id}") // "user_id"라는 키에 아이디를 저장
                                        editor.putBoolean("isLoggedIn", true)
                                        editor.putLong("lastLoginTime", System.currentTimeMillis()) // 사용자가 로그인한 시간을 저장
                                        editor.apply()
                                    }else{
                                        startUserJoinActivity()
                                    }
                                }
                            }
                        }
                    }


                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_login)

        val et_btn_id : EditText = findViewById(R.id.et_user_login_id)
        val et_btn_pw : EditText = findViewById(R.id.et_user_login_pw)
        val btn_login : Button = findViewById(R.id.btn_user_login_login)
        val btn_join : Button = findViewById(R.id.btn_user_login_join)
        // 카카오 로그인을 위한 부분
        val btn_kakao_login : ImageButton = findViewById(R.id.btn_kakao_login)



        // 06/01 이영재
        // 사용자의 로그인 상태를 저장하기 위한 값입니다.
        // 모바일 내부에 파일 형태로 저장합니다.
        sharedPreferences = getSharedPreferences("my_app", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        // 사용자 로그인 상태 확인 및 처리
        checkLoginStatus()


        // Login
        btn_login.setOnClickListener {
            var id : String = et_btn_id.text.toString()
            var pw : String = et_btn_pw.text.toString()

            // TODO: DB에 회원 id와  비밀번호를 확인합니다.
            // TODO: select 해서 가져오는 값이 있는 경우 true를 반환합니다. 없는 경우 로그인에 실패합니다.


            // test 예시
            //UserControl을 만들어 아이디아 비밀번호가 있는지 확인한다.
            //val UserVO : UserVO = UserVO()
            //UserVO.userId, User.userpw사용
             if(id == "test" && pw == "1234") {
                 // 사용자가 로그인 아이디를 저

                 editor.putString("user_id", id) // "user_id"라는 키에 아이디를 저장
                 editor.putBoolean("isLoggedIn", true)
                 editor.putLong("lastLoginTime", System.currentTimeMillis()) // 사용자가 로그인한 시간을 저장
                 editor.apply()

                 val intent = Intent(
                     this@UserLoginActivity,
                     MainActivity::class.java
                 )
                 startActivity(intent)
                 Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                 // 이동하고 stack 삭제
                 finish()
             }else{
                 et_btn_id.setText("")
                 et_btn_pw.setText("")
                 Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
             }

        }



        btn_join.setOnClickListener {
            val intent = Intent(this@UserLoginActivity, UserJoinActivity::class.java)
            startActivity(intent)
        }
        
        // kakaoLogin 구현
        btn_kakao_login.setOnClickListener {
            // Hash키 파악하기용
            try {
                val packageName = packageName
                val packageInfo: PackageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
                for (signature: Signature in packageInfo.signatures) {
                    val md: MessageDigest = MessageDigest.getInstance("SHA")
                    md.update(signature.toByteArray())
                    val hashKey: String = String(Base64.encode(md.digest(), 0))
                    Log.d("HASH_KEY", "Debug Hash Key: $hashKey")
                }
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            } catch (e: NoSuchAlgorithmException) {
                e.printStackTrace()
            }
            ///
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
                // 카카오톡 로그인
                UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                    // 로그인 실패 부분
                    if (error != null) {
                        Log.e(TAG, "로그인 실패 $error")
                        Log.d("TAG", TAG)
                        Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
                        // 사용자가 취소
                        if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                            return@loginWithKakaoTalk
                        }
                        // 다른 오류
                        else {
                            UserApiClient.instance.loginWithKakaoAccount(
                                this,
                                callback = mCallback
                            ) // 카카오 이메일 로그인
                        }
                    }
                    // 로그인 성공 부분
                    else if (token != null) {
                            Log.e(ContentValues.TAG, "로그인 성공 ${token.accessToken}")
                            UserApiClient.instance.me { user, error ->
                                if (error != null) {
                                    Log.e(TAG, "사용자 정보 요청 실패", error)
                                    Toast.makeText(this, "사용자 정보 요청 실패", Toast.LENGTH_SHORT).show()
                                }
                                else if (user != null) {
                                    var scopes = mutableListOf<String>()

                                    if (user.kakaoAccount?.emailNeedsAgreement == true) { scopes.add("account_email") }
                                    //scope 목록을 전달하여 카카오 로그인 요청
                                    UserApiClient.instance.loginWithNewScopes(this, scopes) { token, error ->
                                        if (error != null) {
                                            Log.e(TAG, "사용자 추가 동의 실패", error)
                                            Toast.makeText(this, "사용자 추가 동의 실패", Toast.LENGTH_SHORT).show()
                                        } else {
                                            Log.d(TAG, "allowed scopes: ${token!!.scopes}")

                                            // 사용자 정보 재요청
                                            UserApiClient.instance.me { user, error ->
                                                if (error != null) {
                                                    Log.e(TAG, "사용자 정보 요청 실패", error)
                                                    Toast.makeText(this, "사용자 정보 요청 실패", Toast.LENGTH_SHORT).show()
                                                }
                                                else if (user != null) {
                                                    Log.i(TAG, "사용자 정보 요청 성공" +
                                                            "\n회원번호: ${user.id}" +
                                                            "\n이메일: ${user.kakaoAccount?.email}")
                                                    // TODO : DB 체크를 필요합니다.
                                                    //  카카오 등록이 된 이메일이 있는지 확인합니다.
                                                    var DBKakaoCheck : Boolean = true
                                                    if(DBKakaoCheck){
                                                        startMainActivity()
                                                        editor.putString("user_id", "${user.id}") // "user_id"라는 키에 아이디를 저장
                                                        editor.putBoolean("isLoggedIn", true)
                                                        editor.putLong("lastLoginTime", System.currentTimeMillis()) // 사용자가 로그인한 시간을 저장
                                                        editor.apply()
                                                    }else{
                                                        startUserJoinActivity()
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                        val intent = Intent(this@UserLoginActivity, MainActivity::class.java)
                        startActivity(intent)
                    }
                }
            } else {
                UserApiClient.instance.loginWithKakaoAccount(this, callback = mCallback) // 카카오 이메일 로그인
            }
        }

    }
    private fun startUserJoinActivity() {
        val intent = Intent(this@UserLoginActivity, UserJoinActivity::class.java)
        startActivity(intent)
    }
    private fun startMainActivity() {
        val intent = Intent(this@UserLoginActivity, MainActivity::class.java)
        startActivity(intent)
    }
    // login check 함수
    private fun checkLoginStatus() {

        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)
        val lastLoginTime = sharedPreferences.getLong("lastLoginTime", 0)

        if (isLoggedIn) {
            val currentTime = System.currentTimeMillis()
            val elapsedTime = currentTime - lastLoginTime
            val sevenDaysInMillis = 7 * 24 * 60 * 60 * 1000 // 7일을 밀리초로 변환

            if (elapsedTime > sevenDaysInMillis) {
                // 사용자가 7일 동안 로그인하지 않은 경우, 로그인 정보 삭제
                editor.putBoolean("isLoggedIn", false)
                editor.remove("lastLoginTime")
                editor.apply()
            } else {
                // 사용자가 7일 이내에 로그인한 경우, 메인 화면으로 이동
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }
}
