package com.midas.midaschick.ui.coccidiosis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.midas.midaschick.databinding.ActivityCoccidiosisBinding


class CoccidiosisActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoccidiosisBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoccidiosisBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}