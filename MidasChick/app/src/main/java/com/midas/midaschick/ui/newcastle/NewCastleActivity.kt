package com.midas.midaschick.ui.newcastle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.midas.midaschick.databinding.ActivityNewCastleBinding
import com.midas.midaschick.ui.main.MainActivity


class NewCastleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewCastleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewCastleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnHome.setOnClickListener {
            val move = Intent(this, MainActivity::class.java)
            startActivity(move)
        }
    }
}