package com.tesji.videollamadas_y_llamadas_de_vox

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tesji.videollamadas_y_llamadas_de_vox.databinding.ActivityLoginBinding
import com.zegocloud.uikit.prebuilt.call.ZegoUIKitPrebuiltCallService
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationConfig

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener{
            val userId = binding.userId.text.toString()
            if(userId.isNotEmpty()){
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                intent.putExtra("userID", userId)
                startActivity(intent)
                setUpZegoUiKit(userId)
            }else{
                Toast.makeText(application, "Por Favor ingresa un nombre de usuario", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Configuracion de la API de ZEGOCLOUD
    private fun setUpZegoUiKit(userId: String){
        val application: Application = application
        val appID : Long  = 111 // Tu appID de ZEGOCLOUD
        val appSign : String = "Tu sign de ZEGOCLOUD";

        val userName : String = userId;

        val callInvitationConfig = ZegoUIKitPrebuiltCallInvitationConfig()

        ZegoUIKitPrebuiltCallService.init(application, appID, appSign, userId, userName, callInvitationConfig)
    }

    override fun onDestroy() {
        super.onDestroy()
        ZegoUIKitPrebuiltCallService.unInit()
    }
}