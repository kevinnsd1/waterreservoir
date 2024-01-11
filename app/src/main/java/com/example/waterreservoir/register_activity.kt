package com.example.waterreservoir

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Toast
import com.example.waterreservoir.databinding.ActivityLoginBinding
import com.example.waterreservoir.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class register_activity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.buttomregister.setOnClickListener {
            val email = binding.etUsernameregister.text.toString()
            val pass = binding.passwordregister.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()) {
                firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this, ActivityLoginBinding::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, "Registration Failed: ${it.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Empty Fields are not allowed", Toast.LENGTH_SHORT).show()
            }
        }

        binding.backlogin.setOnClickListener {
            val intent = Intent(this, ActivityLoginBinding::class.java)
            startActivity(intent)
            finish()
        }
    }
}
