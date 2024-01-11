package com.example.waterreservoir

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Toast
import com.example.waterreservoir.databinding.ActivityUploadBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class upload_activity : AppCompatActivity() {
    private lateinit var binding: ActivityUploadBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.saveButton.setOnClickListener {
            saveData()
        }
        database = FirebaseDatabase.getInstance().getReference("User")
    }

    private fun saveData() {
        val name = binding.uploadName.text.toString()
        val email = binding.uploadOperator.text.toString()
        val alamat = binding.uploadLocation.text.toString()
        val phone = binding.uploadPhone.text.toString()

        // Gunakan nomor telepon sebagai child node
        val userReference = database.child(phone)

        val user = User(name, email, alamat, phone)

        userReference.setValue(user).addOnSuccessListener {
            binding.uploadName.text.clear()
            binding.uploadOperator.text.clear()
            binding.uploadLocation.text.clear()
            binding.uploadPhone.text.clear()
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@upload_activity, Main2Activity::class.java)
            startActivity(intent)
            finish()
        }.addOnFailureListener {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }
        }
}