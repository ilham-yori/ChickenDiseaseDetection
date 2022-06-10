package com.midas.midaschick.ui.listdisease

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.midas.midaschick.R
import com.midas.midaschick.adapter.ListDiseaseAdapter
import com.midas.midaschick.databinding.ActivityListDiseaseBinding
import com.midas.midaschick.model.Disease
import com.midas.midaschick.ui.coccidiosis.CoccidiosisActivity
import com.midas.midaschick.ui.newcastle.NewCastleActivity
import com.midas.midaschick.ui.salmonella.SalmonellaActivity

class ListDiseaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListDiseaseBinding
    private lateinit var rviewUser : RecyclerView
    private val list = ArrayList<Disease>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListDiseaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rviewUser = findViewById(R.id.rv_disease)
        rviewUser.setHasFixedSize(true)

        list.addAll(listUser)
        showRecyclerList()


    }

    private val listUser: ArrayList<Disease>
        get() {
            val dataName = resources.getStringArray(R.array.name)
            val dataPhoto = resources.obtainTypedArray(R.array.gambar)

            val listUser = ArrayList<Disease>()
            for (i in dataName.indices) {
                val disease = Disease(
                    dataName[i],
                    dataPhoto.getResourceId(i, -1)
                )
                listUser.add(disease)
            }
            return listUser
        }

    private fun showRecyclerList() {
        rviewUser.layoutManager = LinearLayoutManager(this)
        val listDiseaseAdapter = ListDiseaseAdapter(list)
        rviewUser.adapter = listDiseaseAdapter

        listDiseaseAdapter.setOnItemClickCallback(object : ListDiseaseAdapter.OnItemClickCallback {
            override fun onItemClicked(data : Disease) {
                if(data.nama == "Coccidiosis"){
                    val move = Intent(this@ListDiseaseActivity, CoccidiosisActivity::class.java)
                    startActivity(move)
                }else if(data.nama == "NewCastleDisease"){
                    val move = Intent(this@ListDiseaseActivity, NewCastleActivity::class.java)
                    startActivity(move)
                }else if (data.nama == "Salmonella"){
                    val move = Intent(this@ListDiseaseActivity, SalmonellaActivity::class.java)
                    startActivity(move)
                }
            }
        })
    }
}