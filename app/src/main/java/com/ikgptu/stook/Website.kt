package com.ikgptu.stook

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ikgptu.stook.databinding.ActivityWebsiteBinding

class Website : AppCompatActivity() {
    private lateinit var binding: ActivityWebsiteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebsiteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ptuMain.setOnClickListener {
            val uri = Uri.parse("https://ptu.ac.in/")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
        binding.mainCampus.setOnClickListener {
            val uri = Uri.parse("https://maincampus.ptu.ac.in/")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
        binding.ptuData.setOnClickListener {
            val uri = Uri.parse("http://data.ptu.ac.in/")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
        binding.ptuExam.setOnClickListener {
            val uri = Uri.parse("http://www.ptuexam.com/")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
        binding.prePaper.setOnClickListener {
            val uri = Uri.parse("https://brpaper.com/ptu/b-tech")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
        binding.noticeBoard.setOnClickListener {
            val uri = Uri.parse("https://ptu.ac.in/noticeboard-main/")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
    }
}