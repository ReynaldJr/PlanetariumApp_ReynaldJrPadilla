package com.planetarium.myapp


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView


class GalleryAdapter(private val galleryList: List<Gallery>) :
    RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {


    class GalleryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val GalleryImageView: ImageView = itemView.findViewById(R.id.ImageView_gallery)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.gallery_item_view, parent, false)
        return GalleryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        val gallery = galleryList[position]
        holder.GalleryImageView.setImageResource(gallery.galleryImage)
    }

    override fun getItemCount(): Int {
        return galleryList.size
    }

}