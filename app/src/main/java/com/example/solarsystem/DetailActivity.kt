package com.example.solarsystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import kotlinx.android.synthetic.main.activity_detail.*
import kotlin.properties.Delegates

class DetailActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var galleryList:ArrayList<Gallery>
    private lateinit var galleryAdapter: GalleryAdapter
    private var galleryImage1: Int = 0
    private var galleryImage2: Int = 0
    private var galleryImage3: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val planet = intent.getParcelableExtra<Planet>("Planet")

        if(planet != null) {
            imageView_planet_detail.setImageResource(planet.image)
            textView_planet_detail_title.text = planet.title
            textView_planet_detail_description.text = planet.shortDescription
            detailBackgroundImage.setImageResource(planet.backgroundImage)
            textView_planet_detail_overview_content.text = planet.longDescription
            textView_day_desc.text= planet.day
            textView_year_desc.text = planet.year
            textView_radius_desc.text = planet.radius
            textView_planet_type_desc.text = planet.planetType
            textView_moons_desc.text = planet.moons
            galleryImage1 = planet.gallery_1
            galleryImage2 = planet.gallery_2
            galleryImage3 = planet.gallery_3
        }

        init()

        back_button.setOnClickListener() {
            onBackPressed()
        }
    }

    private fun init() {
        recyclerView = findViewById(R.id.horizontal_recycler_view)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        val snapHelper : SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)
        galleryList = ArrayList()

        addDataToList()

        galleryAdapter = GalleryAdapter(galleryList)
        recyclerView.adapter = galleryAdapter
    }

    private fun addDataToList() {

        galleryList.add(Gallery(galleryImage1))
        galleryList.add(Gallery(galleryImage2))
        galleryList.add(Gallery(galleryImage3))

    }
}