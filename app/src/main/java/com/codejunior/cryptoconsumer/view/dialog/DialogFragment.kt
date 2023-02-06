package com.codejunior.cryptoconsumer.view.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.content.ContextCompat
import com.codejunior.cryptoconsumer.R
import com.codejunior.cryptoconsumer.databinding.FragmentDialogBinding

class DialogFragment(private val onCallback:OnCallback) : androidx.fragment.app.DialogFragment() {
    private lateinit var  bindingDialog:FragmentDialogBinding
     interface OnCallback{
        fun onRetry()
        fun exitApp()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingDialog = FragmentDialogBinding.inflate(layoutInflater)

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
            dialog!!.dismiss()
            onCallback.onRetry()

        }
        bindingDialog.btnExitApp.setOnClickListener{
            dialog!!.dismiss()
            onCallback.exitApp()
        }

        bindingDialog.txtTitleNotConnect.text = arguments?.getString("id")

    }
}