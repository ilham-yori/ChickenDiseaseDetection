package com.midas.midaschick.ui.salmonella

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.midas.midaschick.databinding.ActivityOnBoardingBinding
import com.midas.midaschick.databinding.ActivitySalmonellaBinding

class SalmonellaActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySalmonellaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySalmonellaBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}