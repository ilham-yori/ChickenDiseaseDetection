package com.midas.midaschick.ui.coccidiosis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.midas.midaschick.databinding.ActivityCoccidiosisBinding
import com.midas.midaschick.ui.main.MainActivity


class CoccidiosisActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoccidiosisBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoccidiosisBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnHome.setOnClickListener {
            val move = Intent(this, MainActivity::class.java)
            startActivity(move)
        }
    }
}