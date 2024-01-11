package com.example.waterreservoir

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.util.Patterns
import android.widget.Toast
import com.example.waterreservoir.databinding.ActivityForgotBinding
import com.example.waterreservoir.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class forgot_activity : AppCompatActivity() {
    private lateinit var binding: ActivityForgotBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.next2.setOnClickListener {
            val email: String = binding.etEmail3.text.toString().trim()

            if (email.isEmpty()) {
                binding.etEmail3.error = "Email dont empty"
                binding.etEmail3.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.etEmail3.error = "Email tidak valid"
                binding.etEmail3.requestFocus()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener {
                if (it.isSuccessful){
                    Toast.makeText(this, "Check your email", Toast.LENGTH_SHORT).show()
                    Intent(this, ActivityLoginBinding::class.java).also {
                        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(it)
                    }
                }else{
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
        binding.backlogin.setOnClickListener {
            val intent = Intent(this , ActivityLoginBinding::class.java)
            startActivity(intent)
            }
    }
}