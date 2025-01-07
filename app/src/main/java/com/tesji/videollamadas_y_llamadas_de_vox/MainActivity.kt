package com.tesji.videollamadas_y_llamadas_de_vox

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tesji.videollamadas_y_llamadas_de_vox.databinding.ActivityMainBinding
import com.zegocloud.uikit.service.defines.ZegoUIKitUser

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val userId = intent.getStringExtra("userID")
        binding.tvNameUserID.text = "Hola $userId. \n A quien deseas llamar?"
        binding.etNameUserID.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val userName = binding.etNameUserID.text.toString()
                if(userName.isNotEmpty()){
                    startVideoCall(userName)
                    startCall(userName)
                }else{
                    Toast.makeText(application, "Ingrese un nombre de usuario", Toast.LENGTH_SHORT).show()
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }

    // Funcion para realizar la videollamada
    private fun startVideoCall(userId: String){
        val targetUserName: String  = userId  // The username of the user you want to call.

        binding.btnVideoLlamada.setIsVideoCall(true)
        binding.btnVideoLlamada.resourceID = "zego_uikit_call"
        binding.btnVideoLlamada.setInvitees(listOf(ZegoUIKitUser(userId, targetUserName)))
    }

    // Funcion para realizar la llamada
    private fun startCall(userId: String){
        val targetUserName: String  = userId  // The username of the user you want to call.

        binding.btnLlamada.setIsVideoCall(false)
        binding.btnLlamada.resourceID = "zego_uikit_call"
        binding.btnLlamada.setInvitees(listOf(ZegoUIKitUser(userId, targetUserName)))
    }
}