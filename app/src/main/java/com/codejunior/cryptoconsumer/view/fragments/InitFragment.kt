package com.codejunior.cryptoconsumer.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.codejunior.cryptoconsumer.databinding.FragmentInitBinding

/**
 * A simple [Fragment] subclass.
 * Use the [InitFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InitFragment : Fragment() {

    private lateinit var binding:FragmentInitBinding;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInitBinding.inflate(inflater,container,false)
        return binding.root
    }
}