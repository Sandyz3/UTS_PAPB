package com.example.testprototype

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.testprototype.databinding.ActivityConfirmBinding
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*

class ConfirmActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConfirmBinding
    private lateinit var type: Array<String>
    private lateinit var service: Array<String>
    val servicePrices = arrayOf(0, 0 ,100000, 250000, 1300000, 2000000)
    private lateinit var payments: Array<String>
    private lateinit var adapterPaymentPick1: ArrayAdapter<String>
    private lateinit var adapterPaymentPick2: ArrayAdapter<String>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gunpla = intent.getSerializableExtra("gunplaData") as Gunpla

        type = resources.getStringArray(R.array.type)
        service = resources.getStringArray(R.array.service)
        payments = resources.getStringArray(R.array.payments)

        with(binding){
            tvBack.setOnClickListener{
                finish()
            }

            val adapterService = ArrayAdapter(this@ConfirmActivity, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, service)
            spinnerService.adapter = adapterService
            val adapterType = ArrayAdapter(this@ConfirmActivity, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, type)
            spinnerType.adapter = adapterType
            val adapterPayment = ArrayAdapter(this@ConfirmActivity, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, payments)
            spinnerPayment.adapter = adapterPayment

            spinnerType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedservicePrice = servicePrices[position]
                    tvPriceSet.text = "Rp$selectedservicePrice"
                    tvTotPrice.text = tvPriceSet.text
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    tvPriceSet.text ="Rp0-"
                    tvTotPrice.text = tvPriceSet.text
                }
            }

            adapterPaymentPick1 = ArrayAdapter(this@ConfirmActivity, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, resources.getStringArray(R.array.payment1))
            adapterPaymentPick2 = ArrayAdapter(this@ConfirmActivity, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, resources.getStringArray(R.array.payment2))

            spinnerPayment.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                    when (position) {
                        1 -> spinnerPaymentPick.adapter = adapterPaymentPick1
                        2 -> spinnerPaymentPick.adapter = adapterPaymentPick2
                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }
            }

            btnTanggal.setOnClickListener{
                val datePicker = MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Select Date")
                    .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                    .build()
                datePicker.show(supportFragmentManager, "datePicker")
                datePicker.addOnPositiveButtonClickListener {
                    val simpleDateFormat = SimpleDateFormat("yyyy-MMM-dd", Locale.getDefault())
                    tvTanggalSet.text=simpleDateFormat.format(Date(it).time)
                }
            }
            btnOrder.setOnClickListener {
                val selectedDate = tvTanggalSet.text.toString()
                val selectedAccNumber = accNumber.text.toString()
                val selectedPayment = spinnerPayment.selectedItem.toString()
                val selectedPaymentPick = spinnerPaymentPick.selectedItem.toString()
                val selectedService = spinnerService.selectedItem.toString()
                val selectedType = spinnerType.selectedItem.toString()

                if (selectedDate.isNotBlank() && selectedDate != "Select" &&
                    selectedAccNumber.isNotBlank() &&
                    selectedPayment != "Select" &&
                    selectedPaymentPick != "Select" &&
                    selectedService != "Select" &&
                    selectedType != "Select"
                ) {
                    val currentDate = SimpleDateFormat("ddMMyyyy", Locale.getDefault()).format(Date())
                    val random = (1..100).random()
                    val orderNumber = "$currentDate-$random"

                    val intent = Intent(this@ConfirmActivity, SummaryActivity::class.java)
                    intent.putExtra("gunplaData", gunpla)
                    intent.putExtra("selectedDate", selectedDate)
                    intent.putExtra("accNumber", selectedAccNumber)
                    intent.putExtra("payment", selectedPayment)
                    intent.putExtra("paymentPick", selectedPaymentPick)
                    intent.putExtra("service", selectedService)
                    intent.putExtra("type", selectedType)
                    intent.putExtra("price", tvTotPrice.text)
                    intent.putExtra("orderNumber", orderNumber)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@ConfirmActivity, "Please input all the required information", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}