package com.codejunior.cryptoconsumer.view.fragments

import android.os.Build
import android.os.Bundle
import android.util.Base64
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.bumptech.glide.Glide
import com.codejunior.cryptoconsumer.R
import com.codejunior.cryptoconsumer.databinding.FragmentInformationBinding
import com.codejunior.cryptoconsumer.network.retrofit.model.information.Percent
import com.codejunior.cryptoconsumer.network.room.entities.CryptoEntity
import com.codejunior.cryptoconsumer.view.adapter.PercentAdapter

class InformationFragment : Fragment() {
    private lateinit var binding: FragmentInformationBinding
    private val arg: InformationFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInformationBinding.inflate(
            LayoutInflater.from(inflater.context),
            container,
            false
        )
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val modelCrypto = arg.crypto

        binding.nameCrypto.text = modelCrypto.name
        binding.descriptionCrypto.text = modelCrypto.description
        binding.txtSymbol.text = modelCrypto.symbol

        val imageByteArray: ByteArray = Base64.decode(modelCrypto.logoBase64, Base64.DEFAULT)
        Glide.with(binding.imgCrypto.context).load(imageByteArray).error(R.drawable.bit)
            .into(binding.imgCrypto)


        binding.txtMaxSupply.text = modelCrypto.supplyMax
        binding.txtCirculationSupply.text = modelCrypto.supplyCirculation

        binding.txtPrice.text = modelCrypto.price
        binding.txtRank.text = modelCrypto.rankList.toString()
        binding.txtAddCrypto.text = modelCrypto.dateAddCoinMarket

        binding.rvPorcente.layoutManager = GridLayoutManager(context,2)
        binding.rvPorcente.adapter = PercentAdapter(
            arrayListOf<Percent>(
                Percent("1 HORA", modelCrypto.percent1H!!),
                Percent("24 HORAS", modelCrypto.percent24H!!),
                Percent("7 DIAS", modelCrypto.percent7D!!),
                Percent("30 DIAS", modelCrypto.percent30D!!),
                Percent("60 DIAS", modelCrypto.percent60D!!),
                Percent("90 DIAS", modelCrypto.percent90D!!),
            )
        )

    }

}