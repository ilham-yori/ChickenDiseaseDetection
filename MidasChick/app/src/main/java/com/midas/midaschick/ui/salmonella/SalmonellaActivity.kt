package com.midas.midaschick.ui.salmonella

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.midas.midaschick.databinding.ActivitySalmonellaBinding
import com.midas.midaschick.ui.main.MainActivity


class SalmonellaActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySalmonellaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySalmonellaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnHome.setOnClickListener {
            val move = Intent(this, MainActivity::class.java)
            startActivity(move)
        }
    }
}