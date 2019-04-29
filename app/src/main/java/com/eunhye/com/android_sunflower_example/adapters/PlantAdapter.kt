package com.eunhye.com.android_sunflower_example.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.TextView
import com.eunhye.com.android_sunflower_example.R
import com.eunhye.com.android_sunflower_example.data.Plant

class PlantAdapter: RecyclerView.Adapter<PlantAdapter.ViewHolder>() {

    var values: List<Plant> = listOf()
        set(items) {
            field = items
            notifyDataSetChanged()
        }

    private val onClickListener = View.OnClickListener { view ->
        val item = view.tag as Plant
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            idView.text = values[position].plantId
            contentView.text = values[position].name
            with(itemView) {
                tag = values[position]
                setOnClickListener(onClickListener)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.list_item_plant, parent, false))
    }

    override fun getItemCount() = values.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val idView: TextView = itemView.findViewById(R.id.id_text)
        val contentView: TextView = itemView.findViewById(R.id.content)
    }

}
