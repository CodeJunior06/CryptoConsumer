package com.codejunior.cryptoconsumer.view.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.codejunior.cryptoconsumer.R
import com.codejunior.cryptoconsumer.databinding.FragmentDialogBinding
import com.codejunior.cryptoconsumer.view.fragments.SplashFragmentDirections
import javax.inject.Singleton

class DialogFragment() : androidx.fragment.app.DialogFragment() {
    private lateinit var  bindingDialog:FragmentDialogBinding
    private val args: DialogFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingDialog = FragmentDialogBinding.inflate(inflater,container,false)

        if (dialog != null && dialog!!.window != null) {
            dialog!!.window!!.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.color.transparent
                )
            )
            dialog!!.window!!.requestFeature(Window.FEATURE_NO_TITLE)
            dialog!!.setCancelable(false)
        }

        return bindingDialog.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindingDialog.btnRetry.setOnClickListener{
            findNavController().previousBackStackEntry!!.savedStateHandle["retry"] = true
            findNavController().navigate(DialogFragmentDirections.actionDialogFragmentToSplashFragment())
            dialog!!.dismiss()
        }
        bindingDialog.btnExitApp.setOnClickListener{
            dialog!!.dismiss()
            requireActivity().finish()
        }

        bindingDialog.txtTitleNotConnect.text = args.tittle

    }

    override fun onDestroy() {
        super.onDestroy()
        println("ON DESTROY DIALOG")
    }
}