package com.codejunior.cryptoconsumer.view.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codejunior.cryptoconsumer.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private var bindingSplash:ActivitySplashBinding? = null;
    private val _biding get() = bindingSplash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingSplash = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(_biding!!.root)
    }
}