package com.example.gamgam.applicationforyandex.Activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.View
import android.widget.Toast
import com.example.gamgam.applicationforyandex.NoConnectivityException
import com.example.gamgam.applicationforyandex.PosterGalleryAdapter
import com.example.gamgam.applicationforyandex.R
import com.example.gamgam.applicationforyandex.TheMovieDB_API.Controller
import com.example.gamgam.applicationforyandex.TheMovieDB_API.Result
import com.example.gamgam.applicationforyandex.TheMovieDB_API.TheMovieDBApi
import es.dmoral.toasty.Toasty
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var theMovieDBApi: TheMovieDBApi
    private lateinit var mResults:ArrayList<Result>
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar_mainactivity)
        val layoutManager = GridLayoutManager(this, 2)
        rv_images.setHasFixedSize(true)
        rv_images.layoutManager = layoutManager
        theMovieDBApi = Controller.getApi(this)
        mResults=ArrayList()
        rv_images.adapter = getAdapterForRecyclerView(mResults)
        refheshView()
        getDataList()
        simpleSwipeRefreshLayout.setOnRefreshListener {
            getDataList()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_info_menu,menu)
        return true
    }

    private fun getAdapterForRecyclerView(mResults:ArrayList<Result>)= PosterGalleryAdapter(mResults) { itemView: View, position: Int ->
        if (position != RecyclerView.NO_POSITION) {
            val intent = Intent(itemView.context, SpacePhotoActivity::class.java)
            intent.putParcelableArrayListExtra(resources.getString(R.string.id_array_intent), mResults)
            intent.putExtra(resources.getString(R.string.id_position_intent), position)
            itemView.context.startActivity(intent)
        }
    }
    private fun refheshView(){
        rv_images.adapter.notifyDataSetChanged()
        simpleSwipeRefreshLayout.isRefreshing=false

        }
    private fun showTypeOfError(error:Throwable){
        when(error){
            is NoConnectivityException -> {
                Toasty.error(applicationContext,"Check your internet connection",Toast.LENGTH_SHORT,true).show()
            }
            else ->{
                Toasty.error(applicationContext,"Unknown error",Toast.LENGTH_SHORT,true).show()
            }
        }
        simpleSwipeRefreshLayout.isRefreshing=false

    }
    private fun getDataList(){
        mResults.clear()
        rv_images.adapter.notifyDataSetChanged()
        theMovieDBApi.getDate()
                .subscribeOn(Schedulers.io())
                .doOnNext { item -> mResults.addAll(item.results) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({refheshView()},{ error ->
                    showTypeOfError(error)})
    }
}