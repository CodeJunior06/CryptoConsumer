package com.codejunior.cryptoconsumer.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.codejunior.cryptoconsumer.databinding.FragmentSplashBinding
import com.codejunior.cryptoconsumer.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class SplashFragment : Fragment() {

    private lateinit var binding:FragmentSplashBinding
    private val _viewModelSplash:SplashViewModel by activityViewModels { defaultViewModelProviderFactory }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       binding = FragmentSplashBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _viewModelSplash.invoke()

        observerMessage()

    }

    private fun observerMessage() {
        lifecycleScope.launchWhenCreated {

            _viewModelSplash.messageState.collect{
                if(it.isNotEmpty()) Toast.makeText(context,it,Toast.LENGTH_LONG).show()
            }
        }

    }
}