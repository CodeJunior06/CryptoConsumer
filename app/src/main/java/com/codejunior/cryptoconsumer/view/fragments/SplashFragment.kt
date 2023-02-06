package com.codejunior.cryptoconsumer.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.codejunior.cryptoconsumer.databinding.FragmentSplashBinding
import com.codejunior.cryptoconsumer.view.dialog.DialogFragment
import com.codejunior.cryptoconsumer.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment() ,DialogFragment.OnCallback {

    private lateinit var binding: FragmentSplashBinding
    private val _viewModelSplash: SplashViewModel by activityViewModels { defaultViewModelProviderFactory }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _viewModelSplash.invoke()

        observerMessage()

    }

    private fun observerMessage() {
        lifecycleScope.launchWhenCreated {

            _viewModelSplash.messageStateDialog.collect {
                if(it.isNotEmpty()){
                val args = Bundle()
                args.putString("title",it)
                DialogFragment(this@SplashFragment).apply { arguments = args}.show(requireActivity().supportFragmentManager,"")
                }
            }
        }

        lifecycleScope.launchWhenCreated {
            _viewModelSplash.messageStateSuccess.collect {
                binding.textView.text = it
            }
        }


        lifecycleScope.launchWhenCreated {
            _viewModelSplash.navigation.collect {
                if(it) findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToInitFragment())
            }
        }

    }

    override fun onRetry() {
        _viewModelSplash.invoke()
    }

    override fun exitApp() {
        requireActivity().finish()
    }
}