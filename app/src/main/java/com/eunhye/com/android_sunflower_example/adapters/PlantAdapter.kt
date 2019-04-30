package com.eunhye.com.android_sunflower_example.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.eunhye.com.android_sunflower_example.PlantDetailFragment
import com.eunhye.com.android_sunflower_example.R
import com.eunhye.com.android_sunflower_example.data.Plant
import com.eunhye.com.android_sunflower_example.databinding.ListItemPlantBinding

class PlantAdapter : ListAdapter<Plant, PlantAdapter.ViewHolder>(PlantDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val plant = getItem(position)
        holder.apply {
            bind(createOnClickListener(plant.plantId), plant)
            itemView.tag = plant
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemPlantBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    private fun createOnClickListener(plantId: String): View.OnClickListener {
        val bundle = bundleOf(PlantDetailFragment.ARG_ITEM_ID to plantId)
        return Navigation.createNavigateOnClickListener(
            R.id.action_plant_list_fragment_to_plant_detail_fragment, bundle)
    }

    class ViewHolder(
        private val binding: ListItemPlantBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: View.OnClickListener, item: Plant) {
            binding.apply {
                clickListener = listener
                plant = item
                executePendingBindings()
            }
        }
    }

    private class PlantDiffCallback : DiffUtil.ItemCallback<Plant>() {
        override fun areItemsTheSame(oldItem: Plant, newItem: Plant): Boolean {
            return oldItem.plantId == newItem.plantId
        }

        override fun areContentsTheSame(oldItem: Plant, newItem: Plant): Boolean {
            return oldItem == newItem
        }
    }

}
