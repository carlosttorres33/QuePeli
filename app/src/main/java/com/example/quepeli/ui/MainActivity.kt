package com.example.quepeli.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quepeli.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(3000)
        setTheme(R.style.Theme_QuePeli)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}