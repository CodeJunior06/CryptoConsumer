package com.codejunior.cryptoconsumer.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.codejunior.cryptoconsumer.databinding.FragmentCryptoBinding
import com.codejunior.cryptoconsumer.view.adapter.CryptoAdapter
import com.codejunior.cryptoconsumer.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class CryptoFragment : Fragment() {

    private lateinit var bindingCrypto:FragmentCryptoBinding
    private val _bindingCrypto get() = bindingCrypto

    private val mainViewModel:MainViewModel by activityViewModels{defaultViewModelProviderFactory}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingCrypto = FragmentCryptoBinding.inflate(inflater,container,false)
        return _bindingCrypto.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.invoke()
        getObserver()

    }

    private fun getObserver() {

        lifecycleScope.launchWhenCreated {

            mainViewModel.lstCrypto.collect{
                if(it == null || it.isEmpty()){
                    return@collect
                }
                _bindingCrypto.rvLstCrypto.layoutManager = LinearLayoutManager(context);
                _bindingCrypto.rvLstCrypto.adapter = CryptoAdapter(it)
            }

        }

    }

}