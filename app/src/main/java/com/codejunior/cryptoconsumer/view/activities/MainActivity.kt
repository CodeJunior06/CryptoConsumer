package com.codejunior.cryptoconsumer.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.system.Os
import com.codejunior.cryptoconsumer.R
import com.codejunior.cryptoconsumer.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var bindingMain:ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingMain!!.root)
    }
}