package com.midas.midaschick.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.midas.midaschick.databinding.ActivityMainBinding
import com.midas.midaschick.ui.listdisease.ListDiseaseActivity
import com.midas.midaschick.ui.scan.ScanActivity


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnScan.setOnClickListener {
            val move = Intent(this@MainActivity, ScanActivity::class.java)
            startActivity(move)
        }

        binding.btnList.setOnClickListener {
            val move = Intent(this@MainActivity, ListDiseaseActivity::class.java)
            startActivity(move)
        }
    }

}