package com.midas.midaschick.ui.main.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.midas.midaschick.R
import com.midas.midaschick.adapter.SliderAdapter

class DashboardFragment : Fragment() {

    lateinit var vpSlider: ViewPager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        vpSlider = view.findViewById(R.id.vp_slider)

        val arraySlider = ArrayList<Int>()

        arraySlider.add(R.drawable.chicken_runnin)
        arraySlider.add(R.drawable.chickens_in_market)
        arraySlider.add(R.drawable.chicken_enak)

        val sliderAdapter = SliderAdapter(arraySlider, activity)
        vpSlider.adapter = sliderAdapter

        return view
    }


}