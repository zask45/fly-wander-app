package com.example.flywander

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class MainActivity : AppCompatActivity() {

    private val attractionPlaceList = ArrayList<AttractionPlace>()
    private lateinit var rv: RecyclerView
    private val tag = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // menampilkan toolbar agar overflow menu terlihat
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbar.showOverflowMenu()

        attractionPlaceList.addAll(getListAttractionPlace())

        rv = findViewById(R.id.rv_attraction_place)
        rv.setHasFixedSize(true)

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            rv.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        } else {
            rv.layoutManager = LinearLayoutManager(this)
        }
        rv.adapter = AttractionPlaceAdapter(attractionPlaceList)

    }

    private fun getListAttractionPlace(): ArrayList<AttractionPlace> {
        val name = resources.getStringArray(R.array.data_name)
        val location = resources.getStringArray(R.array.data_location)
        val description = resources.getStringArray(R.array.data_description)
        val image = resources.obtainTypedArray(R.array.data_photo)

        val listAttractionPlace = ArrayList<AttractionPlace>()
        for (i in name.indices) {
            val attractionPlace = AttractionPlace(name[i], location[i], description[i], image.getResourceId(i, -1))
            listAttractionPlace.add(attractionPlace)
        }

        Log.d(tag, listAttractionPlace.toString())
        return listAttractionPlace
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(this@MainActivity, AboutActivity::class.java)
        startActivity(intent)
        return super.onOptionsItemSelected(item)
    }
}