package com.planetarium.myapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PlanetAdapter(private var planetList: ArrayList<Planet>) :
    RecyclerView.Adapter<PlanetAdapter.CategoryViewHolder>() {
    var onItemClick : ((Planet) -> Unit)? = null

    class CategoryViewHolder(categoryView: View) : RecyclerView.ViewHolder(categoryView) {
        val imageView: ImageView = categoryView.findViewById(R.id.image_view_planet_category)
        val textView: TextView = categoryView.findViewById(R.id.text_view_planet_title)
        val textViewDescription: TextView = categoryView.findViewById(R.id.text_view_planet_short_description)
        val linearLayout: LinearLayout = categoryView.findViewById(R.id.planet_item)

    }

    fun setFilteredList(pList: List<Planet>) {
        this.planetList = pList as ArrayList<Planet>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.planet_item, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val planet = planetList[position]
        holder.imageView.setImageResource(planet.image)
        holder.textView.text = planet.title
        holder.linearLayout.setBackgroundResource(planet.cardBackground)
        holder.textViewDescription.text = planet.shortDescription

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(planet)
        }
    }

    override fun getItemCount(): Int {
        return planetList.size
    }
}