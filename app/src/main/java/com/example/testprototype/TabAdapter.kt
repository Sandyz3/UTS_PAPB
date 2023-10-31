package com.example.testprototype
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.testprototype.waitingOrder

class TabAdapter(act: fragment_order) : FragmentStateAdapter(act) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> activeOrder()
            1 -> waitingOrder()
            else -> throw IllegalArgumentException("Position out of array")
        }
    }
}