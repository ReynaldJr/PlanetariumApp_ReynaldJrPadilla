package com.example.solarsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

        init()
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


    private fun filterList(query : String?) {
        if(query != null) {
            val filteredList = ArrayList<Planet>()

            for(i in planetList) {
                if(i.title.lowercase(Locale.ROOT).contains(query)) {
                    filteredList.add(i)
                }
            }

            if(filteredList.isEmpty()) {
                Toast.makeText(this, "No results found", Toast.LENGTH_SHORT).show()
            }

            else {
                planetAdapter.setFilteredList(filteredList)
            }
        }
    }

    private fun addDataToList() {
        planetList.add(Planet(R.drawable.mercury, "Mercury", "The Fastest Planet", R.drawable.gradient_mercury_bg))
        planetList.add(Planet(R.drawable.mars_planet, "Mars", "The Red Planet", R.drawable.gradient_mars_bg))
        planetList.add(Planet(R.drawable.earth_planet, "Earth", "Our Home", R.drawable.gradient_earth_bg))

    }
}