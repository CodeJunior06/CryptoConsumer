package com.codejunior.cryptoconsumer.view.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codejunior.cryptoconsumer.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private lateinit var bindingSplash:ActivitySplashBinding
    private val _biding get() = bindingSplash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingSplash = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(_biding.root)
    }
}