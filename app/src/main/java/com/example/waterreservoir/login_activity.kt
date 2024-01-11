package com.example.waterreservoir

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Toast
import com.example.waterreservoir.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class login_activity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater) // Initialize the binding object
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.register.setOnClickListener {
            val intent = Intent(this, register_activity::class.java)
            startActivity(intent)
        }

        binding.tvForget.setOnClickListener {
            val intent = Intent(this, forgot_activity::class.java)
            startActivity(intent)
        }

        binding.login.setOnClickListener {
            val email = binding.etUsername.text.toString()
            val pass = binding.etPassword.text.toString()

            if (!email.isEmpty() && !pass.isEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish() // Optional: Menutup activity saat login berhasil
                        } else {
                            Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Empty Fields are not allowed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
