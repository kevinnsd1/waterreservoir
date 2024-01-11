package com.example.waterreservoir

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.waterreservoir.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadFragment(fragment_home())

        // definisi widget
        binding.navView.setNavigationItemSelectedListener  {

            when(it.itemId){

                R.id.nav_home -> {
                    loadFragment(fragment_home())
                    true
                }

                R.id.nav_settings -> {
                    loadFragment(fragment_pompa())
                    true
                }

                R.id.nav_about -> {
                    loadFragment(profilefragment())
                     true
                }

                else -> false
            }
        }
    }
    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container,fragment)
        transaction.commit()
    }
    }