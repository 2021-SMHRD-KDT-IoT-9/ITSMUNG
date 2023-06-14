package com.example.itsmungapplication.fragment

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.itsmungapplication.NoticeActivity
import com.example.itsmungapplication.R
import com.example.itsmungapplication.StateActivity
import com.example.itsmungapplication.UserInfoActivity
import com.example.itsmungapplication.UserLoginActivity
import com.kakao.sdk.user.UserApiClient
import android.content.ContentValues.TAG
import android.widget.Toast
import com.example.itsmungapplication.api.ApiManager
import com.example.itsmungapplication.api.DogInfoRequest
import com.example.itsmungapplication.api.UserInfoRequest
import com.example.itsmungapplication.vo.UserVO
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause

class MypageFragment : Fragment() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var context: Context

    private lateinit var btn_mypage_change: Button
    private lateinit var btn_mypage_logout : Button
    private lateinit var imgbtn_mypage_state : ImageButton
    private lateinit var imgbtn_mypage_notice : ImageButton

    private lateinit var tv_mypage_dogUserName : TextView
    private lateinit var tv_mypage_userName : TextView
    private lateinit var tv_mypage_userTel : TextView
    private lateinit var tv_mypage_userAccount : TextView
    private lateinit var btn_mypage_kakao : Button

    // TAG for kakaoLogin - kakaoTalk가 없는 경우
    private val mCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        // TODO : DB체크를 통해 해당하는 아이디가 있는지 확인합니다.
        //  없는 경우 UserJoinActivity로 이동합니다.
        //  있는 경우 MainActivity로 들어갑니다.
        if (error != null) {
            Log.e(ContentValues.TAG, "로그인 실패 $error")
            Toast.makeText(requireContext(), "로그인 실패", Toast.LENGTH_SHORT).show()
            // 왜 계속 오류가 뜰까?
            // hash 값 잘못 등록 오류 -> 해결

        } else if (token != null) {
            Log.e(ContentValues.TAG, "로그인 성공 ${token.accessToken}")
            UserApiClient.instance.me { user, error ->
                if (error != null) {
                    Log.e(TAG, "사용자 정보 요청 실패", error)
                    Toast.makeText(requireContext(), "사용자 정보 요청 실패", Toast.LENGTH_SHORT).show()
                }
                else if (user != null) {
                    var scopes = mutableListOf<String>()

                    if (user.kakaoAccount?.emailNeedsAgreement == true) { scopes.add("account_email") }
                    //scope 목록을 전달하여 카카오 로그인 요청
                    UserApiClient.instance.loginWithNewScopes(requireContext(), scopes) { token, error ->
                        if (error != null) {
                            Log.e(TAG, "사용자 추가 동의 실패", error)
                            Toast.makeText(requireContext(), "사용자 추가 동의 실패", Toast.LENGTH_SHORT).show()
                        } else {
                            Log.d(TAG, "allowed scopes: ${token!!.scopes}")

                            // 사용자 정보 재요청
                            UserApiClient.instance.me { user, error ->
                                if (error != null) {
                                    Log.e(TAG, "사용자 정보 요청 실패", error)
                                    Toast.makeText(requireContext(), "사용자 정보 요청 실패", Toast.LENGTH_SHORT).show()
                                }
                                else if (user != null) {
                                    Log.i(TAG, "사용자 정보 요청 성공" +
                                            "\n회원번호: ${user.id}" +
                                            "\n이메일: ${user.kakaoAccount?.email}")
                                    // TODO : 해당 이메일을 DB에 저장 및 등록한다.
                                }
                            }
                        }
                    }


                }
            }
        }
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.context = context
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_mypage, container, false)

        btn_mypage_change = view.findViewById(R.id.btn_mypage_change)
        btn_mypage_logout = view.findViewById(R.id.btn_mypage_logout)
        imgbtn_mypage_state = view.findViewById(R.id.imgbtn_mypage_state)
        imgbtn_mypage_notice = view.findViewById((R.id.imgbtn_mypage_notice))

        // 반려견 이름
        tv_mypage_dogUserName = view.findViewById(R.id.tv_mypage_dogusername)
        tv_mypage_userName = view.findViewById(R.id.tv_mypage_username)
        tv_mypage_userTel = view.findViewById(R.id.tv_mypage_usertel)
        tv_mypage_userAccount = view.findViewById(R.id.tv_mypage_useraccount)
        btn_mypage_kakao = view.findViewById(R.id.btn_mypage_kakao)

        sharedPreferences = context.getSharedPreferences("itsmung", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()

        val userId = sharedPreferences.getString("userId", null)

        var dogName : String? = ""
        var userName : String? = ""
        var nickname : String? = ""
        var userTel : String? = ""
        var regDate : String? = ""
        var kakaoEmail : String? = ""
        var kakaoRegDate : String? = ""
        var processDogId : String? = ""



        val userVO = UserVO()
        userVO.userId = userId

        val userInfoRequest = UserInfoRequest(userVO)
        val dogInfoRequest = DogInfoRequest(userVO)
        // 애견 정보 불러오기
        ApiManager.dogInfo(dogInfoRequest)
        {
            response->
            if(response != null)
            {
                val dog = response.dog
                dogName = dog.dogName

                tv_mypage_dogUserName.setText(dogName + " 보호자님")
            }
        }
        // 유저 정보 불러오기
        ApiManager.userInfo(userInfoRequest)
        {
            response->
            if(response != null)
            {
                val user = response.user
                Log.e("user check", "user : ${user}")
                userName = user.userName
                userTel = user.userTel
                nickname = user.nickname

                tv_mypage_userName.setText(userName + " 님 \uD83C\uDF80")
                tv_mypage_userTel.setText(userTel)
            }
        }
        



        // 카카오 어떻게 아이디를 보여줄 것인가? 정말 연계된 아이디를 보여줄 것인가?
        // DB 카카오 로그인 연계가 되어있는 경우
        val kakaoLoginCheck : Boolean = true
        if(!kakaoLoginCheck){
            tv_mypage_userAccount.setText("")
            btn_mypage_kakao.isEnabled = true
        }
        else{
            tv_mypage_userAccount.setText("카카오 연결 완료")
            btn_mypage_kakao.isEnabled = false
        }



        // 사용자 정보 수정하기
        btn_mypage_change.setOnClickListener {
            val intent = Intent(activity, UserInfoActivity::class.java)
            startActivity(intent)
        }

        // Logout 을 위한 기능
        btn_mypage_logout.setOnClickListener {
            // Logout 을 위한 기능
            editor.remove("userId")
            editor.remove("token")
            editor.apply()
            // 로그아웃
            // kakao SDK 삭제
            UserApiClient.instance.logout { error ->
                if (error != null) {
                    Log.e(TAG, "로그아웃 실패. SDK에서 토큰 삭제됨", error)
                }
                else {
                    Log.i(TAG, "로그아웃 성공. SDK에서 토큰 삭제됨")
                }
            }

            val intent = Intent(activity,UserLoginActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }

        // state (강아지 상태)알림 페이지로 전환
        imgbtn_mypage_state.setOnClickListener {
            val intent = Intent(activity,StateActivity::class.java)
            startActivity(intent)
        }

        // notice (공지사항) 알림 페이지로 전환
        imgbtn_mypage_notice.setOnClickListener {
            val intent = Intent(activity,NoticeActivity::class.java)
            startActivity(intent)
        }

        btn_mypage_kakao.setOnClickListener {
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(requireContext())) {
                // 카카오톡 로그인
                UserApiClient.instance.loginWithKakaoTalk(requireContext()) { token, error ->
                    // 로그인 실패 부분
                    if (error != null) {
                        Log.e(TAG, "로그인 실패 $error")
                        Log.d("TAG", TAG)
                        Toast.makeText(requireContext(), "로그인 실패", Toast.LENGTH_SHORT).show()
                        // 사용자가 취소
                        if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                            return@loginWithKakaoTalk
                        }
                        // 다른 오류
                        else {
                            UserApiClient.instance.loginWithKakaoAccount(
                                requireContext(),
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
                                Toast.makeText(requireContext(), "사용자 정보 요청 실패", Toast.LENGTH_SHORT).show()
                            }
                            else if (user != null) {
                                var scopes = mutableListOf<String>()

                                if (user.kakaoAccount?.emailNeedsAgreement == true) { scopes.add("account_email") }
                                //scope 목록을 전달하여 카카오 로그인 요청
                                UserApiClient.instance.loginWithNewScopes(requireContext(), scopes) { token, error ->
                                    if (error != null) {
                                        Log.e(TAG, "사용자 추가 동의 실패", error)
                                        Toast.makeText(requireContext(), "사용자 추가 동의 실패", Toast.LENGTH_SHORT).show()
                                    } else {
                                        Log.d(TAG, "allowed scopes: ${token!!.scopes}")

                                        // 사용자 정보 재요청
                                        UserApiClient.instance.me { user, error ->
                                            if (error != null) {
                                                Log.e(TAG, "사용자 정보 요청 실패", error)
                                                Toast.makeText(requireContext(), "사용자 정보 요청 실패", Toast.LENGTH_SHORT).show()
                                            }
                                            else if (user != null) {
                                                Log.i(TAG, "사용자 정보 요청 성공" +
                                                        "\n회원번호: ${user.id}" +
                                                        "\n이메일: ${user.kakaoAccount?.email}")
                                                // TODO : 해당 이메일을 DB에 저장 및 등록한다.
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                UserApiClient.instance.loginWithKakaoAccount(requireContext(), callback = mCallback) // 카카오 이메일 로그인
            }
        }

        return view
    }

}
