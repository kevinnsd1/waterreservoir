package com.example.waterreservoir

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.waterreservoir.activity_delete
import com.example.waterreservoir.activity_update
import com.example.waterreservoir.databinding.ActivityMain2Binding
import com.example.waterreservoir.upload_activity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class Main2Activity: AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding
    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.searchButton.setOnClickListener {
            val searchPhone : String = binding.searchPhone.text.toString()
            if  (searchPhone.isNotEmpty()){
                readData(searchPhone)
            }else{
                Toast.makeText(this,"PLease enter the phone number", Toast.LENGTH_SHORT).show()
            }
        }

        binding.mainUpload.setOnClickListener(View.OnClickListener{
            val intent = Intent(this@Main2Activity,upload_activity::class.java)
            startActivity(intent)
            finish()
        })
        binding.mainUpdate.setOnClickListener(View.OnClickListener{
            val intent = Intent(this@Main2Activity, activity_update::class.java)
            startActivity(intent)
        })
        binding.mainDelete.setOnClickListener(View.OnClickListener{
            val intent = Intent(this@Main2Activity, activity_delete::class.java)
            startActivity(intent)
        })
    }
    private fun readData(phone: String) {
        database = FirebaseDatabase.getInstance().getReference("User")
        database.child(phone).get().addOnSuccessListener {
            if (it.exists()){
                val name = it.child("name").value
                val email = it.child("email").value
                val alamat = it.child("alamat").value
                Toast.makeText(this,"Results Found",Toast.LENGTH_SHORT).show()
                binding.searchPhone.text.clear()
                binding.readName.text = name.toString()
                binding.readOperator.text = email.toString()
                binding.readLocation.text = alamat.toString()
            }else{
                Toast.makeText(this,"Phone number does not exist",Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            Toast.makeText(this,"Something went wrong",Toast.LENGTH_SHORT).show()
        }
    }
}