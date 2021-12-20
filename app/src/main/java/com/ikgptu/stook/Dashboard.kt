package com.ikgptu.stook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.ikgptu.stook.databinding.ActivityDashboardBinding

class Dashboard : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    private lateinit var mAuth: FirebaseAuth
    /**Starting of Back Action*/
    override fun onBackPressed() {
        finishAffinity()
        finish()
    }
    /**End of back action*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mAuth = FirebaseAuth.getInstance()
        val currentUser = mAuth.currentUser
        binding.nameText.text = currentUser?.displayName
        Glide.with(this).load(currentUser?.photoUrl).into(binding.profileImage)

        binding.proceedDashboard.setOnClickListener{
            val intent = Intent(this,PtuSelect::class.java)
            startActivity(intent)
        }
    }


}