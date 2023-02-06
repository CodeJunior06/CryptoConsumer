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
import com.codejunior.cryptoconsumer.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment() {

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

        observerMessage()
        observable()
        init()

    }

    private fun init() = _viewModelSplash.invoke()


    private fun observable() {
        findNavController().currentBackStackEntry!!.savedStateHandle.getLiveData<Boolean>("retry")
            .observe(viewLifecycleOwner) {
                if (it) {
                    init()
                }
            }
    }

    private fun observerMessage() {
        lifecycleScope.launchWhenResumed {

            _viewModelSplash.messageStateDialog.collect {
                if (it.isNotEmpty()) {
                    findNavController().navigate(
                        SplashFragmentDirections.actionSplashFragmentToDialogFragment(
                            it
                        )
                    )
                }
            }
        }

        lifecycleScope.launchWhenResumed {
            _viewModelSplash.messageStateSuccess.collect {
                binding.textView.text = it
            }
        }


        lifecycleScope.launchWhenResumed {
            _viewModelSplash.navigation.collect {
                if (it) findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToInitFragment())
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        print("Splash Destroy")
    }
}