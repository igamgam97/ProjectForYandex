package com.example.gamgam.applicationforyandex

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar=findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val layoutManager = GridLayoutManager(this, 2)
        val recyclerView = findViewById<RecyclerView>(R.id.rv_images)
        //recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = layoutManager

        val adapter = ImageGalleryAdapter(this, ImageSpace.getSpacePhotos())
        recyclerView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_info_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item!!.itemId
        if (id === R.id.action_seach) Log.d("mytag","click")
        return super.onOptionsItemSelected(item)
    }
}
