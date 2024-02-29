package com.example.flywander

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AttractionPlaceAdapter(
    private val attractionPlaceList: ArrayList<AttractionPlace>
) : RecyclerView.Adapter<AttractionPlaceAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvDescription: TextView= itemView.findViewById(R.id.tv_item_description)
        val ivImage: ImageView = itemView.findViewById(R.id.iv_attraction_image)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_attraction_place, parent, false)
        return (ViewHolder(view))
    }

    override fun getItemCount(): Int = attractionPlaceList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (name, location, description, photo) = attractionPlaceList[position]
        holder.tvName.text = name
        holder.tvDescription.text = location
        holder.ivImage.setImageResource(photo)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_ATTRACTION_PLACE, attractionPlaceList[position])
            holder.itemView.context.startActivity(intent)
        }
    }
}