package com.example.gamgam.applicationforyandex

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar_mainactivity)
        val layoutManager = GridLayoutManager(this, 2)
        rv_images.setHasFixedSize(true)
        rv_images.layoutManager = layoutManager
        val theMovieDBApi = Controller.getApi()
        val mSpacePhotos=ArrayList<ImageSpace>()
        theMovieDBApi!!.getDate()
                .subscribeOn(Schedulers.io())
                .flatMapIterable { item -> item.results }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{item -> Log.d("mytag", item.toString())}

        val adapter = ImageGalleryAdapter(ImageSpace.getSpacePhotos()){ itemView: View, position: Int,spacePhoto:ImageSpace ->
            if (position != RecyclerView.NO_POSITION) {
                val intent = Intent(itemView.context, SpacePhotoActivity::class.java)
                intent.putExtra(SpacePhotoActivity.EXTRA_SPACE_PHOTO, spacePhoto)
                itemView.context.startActivity(intent)
            }
        }
        rv_images.adapter = adapter
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
