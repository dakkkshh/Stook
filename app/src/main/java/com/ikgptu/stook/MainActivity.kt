package com.ikgptu.stook


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth



class MainActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    /**Only Once*/

    /**End*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()
        val user = mAuth.currentUser
        /**If user is signed in get it into Dashboard else get it into sign-in Activity*/
        Handler(Looper.getMainLooper()).postDelayed({
            if (user != null) {
                val intent = Intent(this, MasterView::class.java)
                startActivity(intent)
            } else {
                val signInIntent = Intent(this, SignIn::class.java)
                startActivity(signInIntent)
            }
        }, 2000)
    }
}