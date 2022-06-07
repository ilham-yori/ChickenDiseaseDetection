package com.midas.midaschick.ui.newcastle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.midas.midaschick.databinding.ActivityNewCastleBinding


class NewCastleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewCastleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewCastleBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}