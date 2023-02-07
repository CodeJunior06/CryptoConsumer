package com.codejunior.cryptoconsumer.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.codejunior.cryptoconsumer.R
import com.codejunior.cryptoconsumer.databinding.FragmentInitBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InitFragment : Fragment() {

    private lateinit var _bindingInit:FragmentInitBinding
     private val binding  get() = _bindingInit
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _bindingInit = FragmentInitBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSuccess.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_splash).navigate(InitFragmentDirections.actionInitFragmentToMain())
        }
    }
}