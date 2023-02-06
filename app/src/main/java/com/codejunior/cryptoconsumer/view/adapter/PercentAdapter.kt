package com.codejunior.cryptoconsumer.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.codejunior.cryptoconsumer.databinding.PercentAdapterBinding
import com.codejunior.cryptoconsumer.network.retrofit.model.information.Percent

class PercentAdapter(private val list:List<Percent>) : RecyclerView.Adapter<PercentAdapter.HolderItem>() {

    private lateinit var binding:PercentAdapterBinding


    inner class HolderItem(val view: View) : ViewHolder(view){

        private var binding = PercentAdapterBinding.bind(view)
        fun render(model: Percent) {
            binding.title.text = model.tittle
            binding.percent.text = model.percent
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderItem {
        binding = PercentAdapterBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return HolderItem(binding.root)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: HolderItem, position: Int) {

        holder.render(list[position])
    }
}