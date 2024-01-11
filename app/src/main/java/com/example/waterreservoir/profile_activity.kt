package com.example.waterreservoir

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.waterreservoir.databinding.ActivityLoginBinding
import com.example.waterreservoir.databinding.ActivityProfileBinding
import com.example.waterreservoir.databinding.FragmentProfilefragmentBinding

class profile_activity : AppCompatActivity() {
    private lateinit var binding: FragmentProfilefragmentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentProfilefragmentBinding.inflate(layoutInflater) // Initialize the binding object
        setContentView(binding.root)

        binding.databmkg.setOnClickListener {
            val intent = Intent(this, activity_bmkg::class.java)
            startActivity(intent)
        }
    }
}