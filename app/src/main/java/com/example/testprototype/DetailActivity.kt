package com.example.testprototype

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testprototype.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val gunpla = intent.getSerializableExtra("gunplaData") as Gunpla

        with(binding){
            tvBack.setOnClickListener{
                finish()
            }
            image.setImageResource(gunpla.imageResourceId)
            tvTitle.text = gunpla.title
            tvDesc.setText(gunpla.desc)


            btnPO2.setOnClickListener {
                val intent = Intent(this@DetailActivity, ConfirmActivity::class.java)
                intent.putExtra("gunplaData", gunpla)
                startActivity(intent)
            }
        }
    }
}