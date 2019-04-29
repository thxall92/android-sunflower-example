package com.eunhye.com.android_sunflower_example

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.eunhye.com.android_sunflower_example.adapters.PlantAdapter
import com.eunhye.com.android_sunflower_example.databinding.FragmentPlantListBinding
import com.eunhye.com.android_sunflower_example.utilities.InjectorUtils
import com.eunhye.com.android_sunflower_example.viewmodels.PlantListViewModel

class PlantListFragment : Fragment() {

    private lateinit var viewModel: PlantListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentPlantListBinding.inflate(inflater, container, false)
        val context = context ?: return binding.root

        val factory = InjectorUtils.providePlantListViewModelFactory(context)
        viewModel = ViewModelProviders.of(this, factory).get(PlantListViewModel::class.java)

        val adapter = PlantAdapter()
        binding.plantList.adapter = adapter
        subscribeUi(adapter)

        return binding.root
    }

    private fun subscribeUi(adapter: PlantAdapter) {
        viewModel.getPlants().observe(this, Observer { plants ->
            if (plants != null) adapter.values = plants
        })
    }
}