package com.codejunior.cryptoconsumer.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.codejunior.cryptoconsumer.databinding.CryptoAdapterBinding

class CryptoAdapter(private val arrayCryptoConsumer:List<String>) : RecyclerView.Adapter<CryptoAdapter.CryptoHolder>() {
    private lateinit var binding: CryptoAdapterBinding

    inner class CryptoHolder(private val view: View) : ViewHolder(view){
        private val  binding: CryptoAdapterBinding by lazy { CryptoAdapterBinding.bind(view) }
        fun render(name:String) {
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoHolder {
        binding = CryptoAdapterBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  CryptoHolder(binding.root)
    }

    override fun getItemCount(): Int = arrayCryptoConsumer.size



    override fun onBindViewHolder(holder: CryptoHolder, position: Int) {

        holder.render(arrayCryptoConsumer[position])
    }
}
