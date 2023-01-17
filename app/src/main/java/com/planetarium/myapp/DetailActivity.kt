package com.planetarium.myapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import kotlinx.android.synthetic.main.activity_detail.*
import android.media.MediaPlayer
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_splash.*

class DetailActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var galleryList:ArrayList<Gallery>
    private lateinit var galleryAdapter: GalleryAdapter
    private var galleryImage1: Int = 0
    private var galleryImage2: Int = 0
    private var galleryImage3: Int = 0
    var mMediaPlayer: MediaPlayer? = null
    private var planetSound:String = ""

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
            planetSound = planet.planetSound
        }

        init()

        back_button.setOnClickListener() {
            onBackPressed()
        }

        val buttonClick = AnimationUtils.loadAnimation(this, R.anim.button_click)
        playButton.setOnClickListener {
            playButton.startAnimation(buttonClick)
            playSound()
        }

        pauseButton.setOnClickListener {
            pauseButton.startAnimation(buttonClick)
            pauseSound()
        }

        stopButton.setOnClickListener {
            stopButton.startAnimation(buttonClick)
            stopSound()
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


    // 1. Plays the sound
    fun playSound() {
        if (mMediaPlayer == null) {
            mMediaPlayer = MediaPlayer.create(this, resources.getIdentifier(planetSound,"raw", packageName))
            mMediaPlayer!!.isLooping = true
            mMediaPlayer!!.start()
        } else mMediaPlayer!!.start()
    }

    // 2. Pause playback
    fun pauseSound() {
        if (mMediaPlayer?.isPlaying == true) mMediaPlayer?.pause()
    }

    // 3. Stops playback
    fun stopSound() {
        if (mMediaPlayer != null) {
            mMediaPlayer!!.stop()
            mMediaPlayer!!.release()
            mMediaPlayer = null
        }
    }

    // 4. Destroys the MediaPlayer instance when the app is closed
    override fun onStop() {
        super.onStop()
        if (mMediaPlayer != null) {
            mMediaPlayer!!.release()
            mMediaPlayer = null
        }
    }
}