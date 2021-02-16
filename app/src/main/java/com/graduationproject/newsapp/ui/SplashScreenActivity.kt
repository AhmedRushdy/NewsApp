package com.graduationproject.newsapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.graduationproject.newsapp.R
import com.graduationproject.newsapp.databinding.ActivityFullscreenBinding


class SplashScreenActivity : AppCompatActivity() {
    lateinit var binding : ActivityFullscreenBinding
    lateinit var videoView: VideoView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullscreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val slideAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_up)
        binding.logo.startAnimation(slideAnimation)
        Handler().postDelayed({
            val intent = Intent(this, NewsActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000) // 3000 is the delayed time in milliseconds.
    }

}


