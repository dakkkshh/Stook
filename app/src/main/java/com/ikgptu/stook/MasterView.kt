package com.ikgptu.stook

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.ikgptu.stook.databinding.ActivityMasterViewBinding


class MasterView : AppCompatActivity() {
    private lateinit var binding: ActivityMasterViewBinding
    private lateinit var mAuth: FirebaseAuth
    /**Starting of Back Action*/
    override fun onBackPressed() {
        finishAffinity()
        finish()
    }
    /**End of back action*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMasterViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mAuth = FirebaseAuth.getInstance()
        val currentUser = mAuth.currentUser
        binding.userName.text = currentUser?.displayName
        //SIGN-OUT Button
        binding.signOutBtn.setOnClickListener {
            mAuth.signOut()
            val intent = Intent(this,SignIn::class.java)
            startActivity(intent)
        }
        //Notification On-Click
        binding.notiActivity.setOnClickListener {
            val intent = Intent(this,NotificationDashboard::class.java)
            startActivity(intent)
        }
        //Website on-Click
        binding.webActivity.setOnClickListener {
            val intent = Intent(this,Website::class.java)
            startActivity(intent)
        }
        //Guideline On-Click
        binding.guideActivity.setOnClickListener {
            val intent = Intent(this,Guidelines::class.java)
            startActivity(intent)
        }
        //Contact On-Click
        binding.contactActivity.setOnClickListener {
            val intent = Intent(this,ContactUs::class.java)
            startActivity(intent)
        }
    }
}