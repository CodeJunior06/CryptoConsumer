package com.codejunior.cryptoconsumer.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.codejunior.cryptoconsumer.databinding.FragmentSplashBinding
import com.codejunior.cryptoconsumer.domain.StateGeneric
import com.codejunior.cryptoconsumer.domain.codeAppToMessage
import com.codejunior.cryptoconsumer.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding: FragmentSplashBinding get() = _binding!!

    private val _viewModelSplash: SplashViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.apply {
            lifecycleScope.launch {
                lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                    _viewModelSplash.stateSplash.collect {
                        when (it) {
                            is StateGeneric.Dialog -> findNavController().navigate(
                                SplashFragmentDirections.actionSplashFragmentToDialogFragment(
                                    requireContext().codeAppToMessage(it.message)
                                )
                            )

                            is StateGeneric.Navigate -> findNavController().navigate(
                                SplashFragmentDirections.actionSplashFragmentToInitFragment()
                            )

                            is StateGeneric.Success<*> -> _viewModelSplash.navigateInitFragment()
                            StateGeneric.Load -> {
                                binding.textView.text = "Obteniendo informacion"
                            }
                        }
                    }
                }
            }
        }

        observableDialog()

        init()
    }

    private fun init() = _viewModelSplash()


    private fun observableDialog() {
        findNavController().currentBackStackEntry!!.savedStateHandle.getLiveData<Boolean>("retry")
            .observe(viewLifecycleOwner) {
                if (it) {
                    init()
                }
            }
    }
}