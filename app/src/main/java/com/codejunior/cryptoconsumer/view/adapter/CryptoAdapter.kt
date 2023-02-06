package com.codejunior.cryptoconsumer.view.adapter

import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.codejunior.cryptoconsumer.R
import com.codejunior.cryptoconsumer.databinding.CryptoAdapterBinding
import com.codejunior.cryptoconsumer.network.room.entities.CryptoEntity

class CryptoAdapter(private val arrayCrypto:List<CryptoEntity>) : RecyclerView.Adapter<CryptoAdapter.CryptoHolder>() {
    private lateinit var binding: CryptoAdapterBinding

    inner class CryptoHolder(private val view: View) : ViewHolder(view){
        private val  binding: CryptoAdapterBinding by lazy { CryptoAdapterBinding.bind(view) }
        fun render(cryptoModel:CryptoEntity) {

            binding.nameCrypto.text = cryptoModel.name
            binding.symbolCrypto.text = cryptoModel.symbol
            binding.valueCrypto.text = "$ ${cryptoModel.price}"
            binding.porcentageCrypto.text = cryptoModel.percent1H
            val imageByteArray: ByteArray = Base64.decode(cryptoModel.logoBase64, Base64.DEFAULT)
            Glide.with(binding.imgCrypto.context).load(imageByteArray)
                .placeholder(R.drawable.bit).error(R.drawable.bit).into(binding.imgCrypto)

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoHolder {
        binding = CryptoAdapterBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  CryptoHolder(binding.root)
    }

    override fun getItemCount(): Int = arrayCrypto.size



    override fun onBindViewHolder(holder: CryptoHolder, position: Int) {

        holder.render(arrayCrypto[position])
    }
}
