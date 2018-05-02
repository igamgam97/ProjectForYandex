package com.example.gamgam.applicationforyandex

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.View
import android.widget.Toast
import es.dmoral.toasty.Toasty
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar_mainactivity)

        val layoutManager = GridLayoutManager(this, 2)
        rv_images.setHasFixedSize(true)
        rv_images.layoutManager = layoutManager
        val theMovieDBApi = Controller.getApi(this)
        val mResults=ArrayList<Result>()
        refheshView(mResults)

        theMovieDBApi.getDate()
                .subscribeOn(Schedulers.io())
                .doOnNext { item -> mResults.addAll(item.results) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({refheshView(mResults)},{ error ->
                    showTypeOfError(error)})

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_info_menu,menu)
        return true
    }

    fun refheshView(mResults:ArrayList<Result>){
        val adapter = PosterGalleryAdapter(mResults){ itemView: View, position: Int, mResult:Result ->
            if (position != RecyclerView.NO_POSITION) {
                val intent = Intent(itemView.context, SpacePhotoActivity::class.java)
                intent.putParcelableArrayListExtra(SpacePhotoActivity.EXTRA_SPACE_PHOTO, mResults)
                intent.putExtra("some_name",position)
                itemView.context.startActivity(intent)
            }
        }
        rv_images.adapter = adapter
        }
    private fun showTypeOfError(error:Throwable){
        when(error){
            is NoConnectivityException -> {
                rv_images.setBackgroundResource(R.drawable.internet_error)
                Toasty.error(applicationContext,"Check your internet connection",Toast.LENGTH_SHORT,true).show()
            }
            else ->{
                rv_images.setBackgroundResource(R.drawable.internet_error)
                Toasty.error(applicationContext,"Unknown error",Toast.LENGTH_SHORT,true).show()
            }
        }

    }
}
