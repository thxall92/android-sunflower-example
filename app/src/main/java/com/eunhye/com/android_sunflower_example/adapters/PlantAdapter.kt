package com.eunhye.com.android_sunflower_example.adapters

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.eunhye.com.android_sunflower_example.PlantDetailFragment
import com.eunhye.com.android_sunflower_example.PlantListFragment
import com.eunhye.com.android_sunflower_example.data.Plant
import com.eunhye.com.android_sunflower_example.R


class PlantAdapter(
    parentActivity: PlantListFragment,
    isTwoPane: Boolean
): RecyclerView.Adapter<PlantAdapter.ViewHolder>() {

    var values: List<Plant> = ArrayList(0)
        set(items) {
            field = items
            notifyDataSetChanged()
        }


    private val onClickListener = View.OnClickListener { view ->
        val item = view.tag as Plant
//        if (isTwoPane) {
//            val fragment = PlantDetailFragment.newInstance(item.plantId)
//            parentActivity.supportFragmentManager.beginTransaction()
//                .replace(R.id.plant_detail_container,fragment).commit()
//        } else {
//            val intent = Intent(view.context, PlantDetailActivity::class.java).apply {
//                putExtra(PlantDetailFragment.ARG_ITEM_ID, item.plantId)
//            }
//            view.context.startActivity(intent)
//        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            Glide.with(imageView.context)
                .load(values[position].imageUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView)
            contentView.text = values[position].name
            with(itemView) {
                tag = values[position]
                setOnClickListener(onClickListener)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(
            com.eunhye.com.android_sunflower_example.R.layout.list_item_plant, parent, false))
    }

    override fun getItemCount() = values.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.plant_item_image)
        val contentView: TextView = itemView.findViewById(R.id.plant_item_title)
    }

}
