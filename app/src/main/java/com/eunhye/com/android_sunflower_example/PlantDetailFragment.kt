package com.eunhye.com.android_sunflower_example

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.eunhye.com.android_sunflower_example.databinding.FragmentPlantDetailBinding

class PlantDetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentPlantDetailBinding.inflate(inflater, container, false)
        return binding.root
    }
}