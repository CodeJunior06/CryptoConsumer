package com.codejunior.cryptoconsumer.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.codejunior.cryptoconsumer.databinding.FragmentCryptoBinding
import com.codejunior.cryptoconsumer.view.adapter.CryptoAdapter

class CryptoFragment : Fragment() {

    private lateinit var bindingCrypto:FragmentCryptoBinding
    private val _bindingCrypto get() = bindingCrypto


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingCrypto = FragmentCryptoBinding.inflate(inflater,container,false)
        return _bindingCrypto.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _bindingCrypto.rvLstCrypto.layoutManager = LinearLayoutManager(context);
        _bindingCrypto.rvLstCrypto.adapter = CryptoAdapter(arrayListOf("OK"))

    }

}