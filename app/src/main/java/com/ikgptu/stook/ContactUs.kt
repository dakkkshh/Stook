package com.ikgptu.stook

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ikgptu.stook.databinding.ActivityContactUsBinding

class ContactUs : AppCompatActivity() {
    private lateinit var binding: ActivityContactUsBinding

    private lateinit var emailTopic : EditText
    private lateinit var emailMessage : EditText

    private lateinit var topic : String
    private lateinit var message : String

    private lateinit var emailButton : ImageView
    private lateinit var whatsappButton : ImageView
    private val email : String = "daksh125001@gmail.com"
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityContactUsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        emailTopic = binding.topicTxt
        emailMessage = binding.messageTxt

        emailButton = binding.emailBtn
        whatsappButton = binding.whatsappBtn

        emailButton.setOnClickListener {
            getData()

            val intent = Intent(Intent.ACTION_SEND)
            intent.putExtra(Intent.EXTRA_EMAIL,arrayOf<String>(email))
            intent.putExtra(Intent.EXTRA_SUBJECT, topic)
            intent.putExtra(Intent.EXTRA_TEXT, message)
            intent.type = "message/rfc822"
            startActivity(Intent.createChooser(intent, "Choose App"))
        }
        whatsappButton.setOnClickListener {
            Toast.makeText(this,"Experimental Feature!", Toast.LENGTH_SHORT).show()
        }

    }
    private fun getData(){
        topic = emailTopic.text.toString()
        message = emailMessage.text.toString()
    }
}