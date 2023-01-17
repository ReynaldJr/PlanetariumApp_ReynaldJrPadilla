package com.planetarium.myapp

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_popup.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var planetCategories: RecyclerView
    private lateinit var planetList: ArrayList<Planet>
    private lateinit var planetAdapter: PlanetAdapter

    private lateinit var searchPlanet: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        displayPopup()
        init()

        // Display pop up on info button
        infoButton.setOnClickListener {
            infoButton.startAnimation(AnimationUtils.loadAnimation(this, R.anim.button_click))
            displayPopup()
        }

    }

    private fun init() {
        searchPlanet = findViewById(R.id.searchView_search_planet)
        planetCategories = findViewById(R.id.recycler_view_planet_categories)

        planetCategories.setHasFixedSize(true)
        planetCategories.layoutManager = GridLayoutManager(this, 2)

        planetList = ArrayList()
        addDataToList()

        planetAdapter = PlanetAdapter(planetList)
        planetCategories.adapter = planetAdapter

        planetAdapter.onItemClick = {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("Planet", it)
            startActivity(intent)
        }

        searchPlanet.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }

        })
    }

    private fun displayPopup() {
        var popupScreen = Dialog(this)
        popupScreen.setCancelable(false)

        popupScreen.setContentView(R.layout.activity_popup)
        popupScreen.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        var okButton = popupScreen.findViewById<Button>(R.id.pop_up_button)
        okButton.setOnClickListener {
            popupScreen.dismiss()
        }
        popupScreen.show()
    }

    private fun filterList(query: String?) {
        if (query != null) {
            val filteredList = ArrayList<Planet>()

            for (i in planetList) {
                if (i.title.lowercase(Locale.ROOT).contains(query.lowercase(Locale.ROOT))) {
                    filteredList.add(i)
                }
            }

            if (filteredList.isEmpty()) {
                Toast.makeText(this, "No results found", Toast.LENGTH_SHORT).show()
            } else {
                planetAdapter.setFilteredList(filteredList)
            }
        }
    }


    private fun addDataToList() {
        planetList.add(
            Planet(
                R.drawable.mercury,
                "Mercury",
                "The Fastest Planet",
                R.drawable.gradient_mercury_bg,
                R.drawable.mercury_image_1,
                getString(R.string.merc_overview),
                getString(R.string.merc_day),
                getString(R.string.merc_year),
                getString(R.string.merc_radius),
                getString(R.string.merc_planet_type),
                getString(R.string.merc_moons),
                R.drawable.mercury_image_3,
                R.drawable.mercury_image_4,
                R.drawable.mercury_image_5,
                "mercury_sound"
            )
        )

        planetList.add(
            Planet(
                R.drawable.venus_planet,
                "Venus",
                "The Brightest Planet",
                R.drawable.gradient_venus_bg,
                R.drawable.venus_image_1,
                getString(R.string.venus_overview),
                getString(R.string.venus_day),
                getString(R.string.venus_year),
                getString(R.string.venus_radius),
                getString(R.string.venus_planet_type),
                getString(R.string.venus_moons),
                R.drawable.venus_image_2,
                R.drawable.venus_image_3,
                R.drawable.venus_image_4,
                "venus_sound"
            )
        )


        planetList.add(
            Planet(
                R.drawable.earth_planet,
                "Earth",
                "Our Home Planet",
                R.drawable.gradient_earth_bg,
                R.drawable.earth_image_1,
                getString(R.string.earth_overview),
                getString(R.string.earth_day),
                getString(R.string.earth_year),
                getString(R.string.earth_radius),
                getString(R.string.earth_planet_type),
                getString(R.string.earth_moons),
                R.drawable.earth_image_2,
                R.drawable.earth_image_3,
                R.drawable.earth_image_4,
                "earth_sound"
            )
        )

        planetList.add(
            Planet(
                R.drawable.mars_planet,
                "Mars",
                "The Red Planet",
                R.drawable.gradient_mars_bg,
                R.drawable.mars_image_1,
                getString(R.string.mars_overview),
                getString(R.string.mars_day),
                getString(R.string.mars_year),
                getString(R.string.mars_radius),
                getString(R.string.mars_planet_type),
                getString(R.string.mars_moons),
                R.drawable.mars_image_2,
                R.drawable.mars_image_3,
                R.drawable.mars_image_4,
                "mars_sound"

            )
        )


        planetList.add(
            Planet(
                R.drawable.jupiter_planet,
                "Jupiter",
                "The Largest Planet",
                R.drawable.gradient_jupiter_bg,
                R.drawable.jupiter_image_1,
                getString(R.string.jupiter_overview),
                getString(R.string.jupiter_day),
                getString(R.string.jupiter_year),
                getString(R.string.jupiter_radius),
                getString(R.string.jupiter_planet_type),
                getString(R.string.jupiter_moons),
                R.drawable.jupiter_image_2,
                R.drawable.jupiter_image_3,
                R.drawable.jupiter_image_4,
                "jupiter_sound"

            )
        )

        planetList.add(
            Planet(
                R.drawable.saturn_planet,
                "Saturn",
                "The Beautiful Rings",
                R.drawable.gradient_saturn_bg,
                R.drawable.saturn_image_1,
                getString(R.string.saturn_overview),
                getString(R.string.saturn_day),
                getString(R.string.saturn_year),
                getString(R.string.saturn_radius),
                getString(R.string.saturn_planet_type),
                getString(R.string.saturn_moons),
                R.drawable.saturn_image_2,
                R.drawable.saturn_image_3,
                R.drawable.saturn_image_4,
                "saturn_sound"
            )
        )

        planetList.add(
            Planet(
                R.drawable.uranus_planet,
                "Uranus",
                "The Tilted Planet",
                R.drawable.gradient_uranus_bg,
                R.drawable.uranus_image_1,
                getString(R.string.uranus_overview),
                getString(R.string.uranus_day),
                getString(R.string.uranus_year),
                getString(R.string.uranus_radius),
                getString(R.string.uranus_planet_type),
                getString(R.string.uranus_moons),
                R.drawable.uranus_image_2,
                R.drawable.uranus_image_3,
                R.drawable.uranus_image_4,
                "uranus_sound"
            )
        )


        planetList.add(
            Planet(
                R.drawable.neptune_planet,
                "Neptune",
                "The Farthest Planet",
                R.drawable.gradient_neptune_bg,
                R.drawable.neptune_image_1,
                getString(R.string.neptune_overview),
                getString(R.string.neptune_day),
                getString(R.string.neptune_year),
                getString(R.string.neptune_radius),
                getString(R.string.neptune_planet_type),
                getString(R.string.neptune_moons),
                R.drawable.neptune_image_2,
                R.drawable.neptune_image_3,
                R.drawable.neptune_image_4,
                "neptune_sound"
            )
        )

    }
}