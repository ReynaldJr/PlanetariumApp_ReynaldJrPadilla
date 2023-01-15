package com.example.solarsystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val planet = intent.getParcelableExtra<Planet>("Planet")

        if(planet != null) {
            textView_planet_detail_title.text = planet.title
            imageView_planet_detail.setImageResource(planet.image)
        }
    }
}