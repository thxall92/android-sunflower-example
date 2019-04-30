package com.eunhye.com.android_sunflower_example

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.eunhye.com.android_sunflower_example.databinding.FragmentPlantDetailBinding
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.eunhye.com.android_sunflower_example.utilities.InjectorUtils
import com.eunhye.com.android_sunflower_example.viewmodels.PlantDetailViewModel


class PlantDetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val plantId = requireNotNull(arguments).getString(ARG_ITEM_ID)
        val factory = InjectorUtils.providePlantDetailViewModelFactory(requireActivity(), plantId)
        val plantDetailViewModel = ViewModelProviders.of(this, factory)
            .get(PlantDetailViewModel::class.java)

        val binding = DataBindingUtil.inflate<FragmentPlantDetailBinding>(
            inflater, R.layout.fragment_plant_detail, container, false).apply {
            viewModel = plantDetailViewModel
            setLifecycleOwner(this@PlantDetailFragment)

        }
        return binding!!.root
    }

    companion object {

        const val ARG_ITEM_ID = "item_id"

        fun newInstance(plantId: String): PlantDetailFragment {
            val bundle = Bundle().apply { putString(ARG_ITEM_ID, plantId) }
            return PlantDetailFragment().apply { arguments = bundle }
        }
    }
}