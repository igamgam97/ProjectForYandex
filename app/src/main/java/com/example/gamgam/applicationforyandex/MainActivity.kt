package com.example.gamgam.applicationforyandex

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.GridLayoutManager



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val layoutManager = GridLayoutManager(this, 2)
        val recyclerView = findViewById<RecyclerView>(R.id.rv_images)
        //recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = layoutManager

        val adapter = ImageGalleryAdapter(this, ImageSpace.getSpacePhotos())
        recyclerView.adapter = adapter
    }
}
