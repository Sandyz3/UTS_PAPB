package com.example.testprototype

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testprototype.databinding.ItemGunplaBinding


class GunplaAdapter(
    var gunplas: List<Gunpla>,
    private var onItemClickListener: ((Gunpla) -> Unit)? = null
) : RecyclerView.Adapter<GunplaAdapter.GunplaViewHolder>() {

    inner class GunplaViewHolder(val binding:ItemGunplaBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GunplaViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemGunplaBinding.inflate(layoutInflater, parent, false)
        return GunplaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GunplaViewHolder, position: Int) {
        holder.binding.apply {
            image.setImageResource(gunplas[position].imageResourceId)
            tvTitle.text = gunplas[position].title
            root.setOnClickListener {
                onItemClickListener?.invoke(gunplas[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return gunplas.size
    }
}