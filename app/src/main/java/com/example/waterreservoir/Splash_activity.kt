package com.example.waterreservoir

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import com.example.waterreservoir.databinding.ActivitySplashBinding

class Splash_activity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivNote.alpha = 0f
        binding.ivNote.animate().setDuration(3000).alpha(1f).withEndAction {
            val i = Intent(this, login_activity::class.java) // Ganti ke login_activity::class.java
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}
