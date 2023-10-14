package com.example.testprototype

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testprototype.databinding.ActivitySummaryBinding
import java.text.SimpleDateFormat
import java.util.*

class SummaryActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySummaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySummaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gunpla = intent.getSerializableExtra("gunplaData") as Gunpla
        val service = intent.getStringExtra("service")
        val date = intent.getStringExtra("selectedDate")
        val inputFormat = SimpleDateFormat("yyyy-MMM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("EEEE, dd-MM-yyyy", Locale.getDefault())
        val formattedDate = outputFormat.format(inputFormat.parse(date))
        val orderNumber = intent.getStringExtra("orderNumber")
        val payment = intent.getStringExtra("payment")
        val paymentPick = intent.getStringExtra("paymentPick")
        val price = intent.getStringExtra("price")
        val type = intent.getStringExtra("type")




        with(binding){
            tvBack.setOnClickListener{
                finish()
            }
            image.setImageResource(gunpla.imageResourceId)
            tvTitle.text = gunpla.title
            tvService.text = service
            tvDate.text = formattedDate
            tvOrderNumber.text = orderNumber
            tvPayment.text = "$payment ($paymentPick)"
            tvType.text = type
            tvPrice.text= price
        }
    }
}