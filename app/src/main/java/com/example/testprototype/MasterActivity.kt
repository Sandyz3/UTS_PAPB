package com.example.testprototype

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.testprototype.databinding.ActivityMasterBinding

class MasterActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMasterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMasterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding){
            replaceFragment(MainActivity())


            bottomNavBar.setOnItemSelectedListener {
                when(it.itemId) {
                    R.id.nav_home -> replaceFragment(MainActivity())
                    R.id.nav_order -> replaceFragment(fragment_order())
                    R.id.nav_profile -> replaceFragment(fragment_profile())

                    else -> {}
                }
                true
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}