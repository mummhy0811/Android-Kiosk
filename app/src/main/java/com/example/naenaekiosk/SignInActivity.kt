package com.example.naenaekiosk

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.naenaekiosk.databinding.ActivitySignInBinding
import kotlin.properties.Delegates

class SignInActivity : AppCompatActivity(), ConfirmDialogInterface {

    private lateinit var binding: ActivitySignInBinding
    private var userId by Delegates.notNull<Long>()
    lateinit var userInfo: SharedPreferences
    private var isMember=true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userInfo = getSharedPreferences("userInfo", 0)
        userInfo.edit().putString("userId", "0").apply()
        userInfo.edit().putString("userPIN", "0000").apply()
        userId = userInfo.getString("userInfo", "2")!!.toLong()

        binding.signUp.setOnClickListener {
            val intent= Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.button4.setOnClickListener{
            //todo 로그인 정보 확인해서 isMember에 넘기기

            if(isMember){
                //todo 유저 정보아이디 저장
                var userId=binding.editTextTextPersonName.text.toString()
                userInfo.edit().putString("userId", userId).apply()
                //todo 유저 핀 정보 수정
                userInfo.edit().putString("userPIN", "1234").apply()
                val intent= Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }else{ //등록된 계정이 아니면 경고
                val dialog = CustomDialog(this@SignInActivity, "잘못된 아이디 또는 비밀번호입니다." , 0, 1)
                dialog.isCancelable = true
                dialog.show(this.supportFragmentManager,"ConfirmDialog")
            }
        }
    }

    override fun onYesButtonClick(num: Int, theme: Int) {
    }
}