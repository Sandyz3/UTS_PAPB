package com.example.testprototype

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import com.example.testprototype.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val username = intent.getStringExtra("usn")

        val gunplaList = mutableListOf(
            Gunpla("LabZero 1/100 Gundam Barbatos Lupus REX",R.drawable.gbarbatos, R.string.desc, 1/100,"Gundam Resin/Conversion Kits",listOf("New Arrivals", "Pre Orders" ,"Real New Arrivals")),
            Gunpla("AEther 1/100 MG Dynasty Warrior",R.drawable.aether,R.string.desc, 1/100,"Third Party Model Kits",listOf("New Arrivals", "Pre Orders" ,"Real New Arrivals")),
            Gunpla("Infinity Nova (In Era+) Thunderbolt",R.drawable.thunderbolt,R.string.desc, 1/100,"Third Party Model Kits",listOf("New Arrivals", "Pre Orders" ,"Real New Arrivals")),
        )

        val adapter = GunplaAdapter(gunplaList) { clickedGunpla ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("gunplaData", clickedGunpla)
            startActivity(intent)
        }

        with(binding){
            tvUsername.text = username
            rvGunpla.adapter = adapter
        }
    }
}
