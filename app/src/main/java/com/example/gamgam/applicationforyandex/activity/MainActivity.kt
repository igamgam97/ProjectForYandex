package com.example.gamgam.applicationforyandex.activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.View
import android.widget.Toast
import com.example.gamgam.applicationforyandex.R
import com.example.gamgam.applicationforyandex.adapters.PosterGalleryAdapter
import com.example.gamgam.applicationforyandex.api.Controller
import com.example.gamgam.applicationforyandex.api.TheMovieDBApi
import com.example.gamgam.applicationforyandex.models.Result
import com.example.gamgam.applicationforyandex.network.NoConnectivityException
import es.dmoral.toasty.Toasty
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(),SwipeRefreshLayout.OnRefreshListener {
    private lateinit var theMovieDBApi: TheMovieDBApi
    private lateinit var mResults: ArrayList<Result>
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar_main_activity)
        with(rv_images){
            setHasFixedSize(true)
            layoutManager=GridLayoutManager(this@MainActivity,2)
            adapter=getAdapterForRecyclerView(mResults)
        }
        theMovieDBApi = Controller.getApi(this)
        getDataList()
        simpleSwipeRefreshLayout.setOnRefreshListener (this)

    }
    override fun onRefresh() {
        getDataList()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_info_menu, menu)
        return true
    }

    private fun getAdapterForRecyclerView(mResults: ArrayList<Result>) = PosterGalleryAdapter(mResults) { itemView: View, position: Int ->
        if (position != RecyclerView.NO_POSITION) {
            val intent = Intent(itemView.context, SpacePhotoActivity::class.java)
            intent.putParcelableArrayListExtra(resources.getString(R.string.id_array_intent), mResults)
            intent.putExtra(resources.getString(R.string.id_position_intent), position)
            itemView.context.startActivity(intent)
        }
    }

    private fun getDataList() {
        //rv_images.adapter.notifyDataSetChanged()
        theMovieDBApi.getDate()
                .subscribeOn(Schedulers.io())
                .filter { item -> item.results.sorted() != mResults }
                .doOnNext { item-> mResults= item.results.sorted<Result>() as ArrayList<Result> }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ refheshView() }, { error ->
                    showTypeOfError(error)
                },{simpleSwipeRefreshLayout.isRefreshing=false})
    }



}
fun MainActivity.showTypeOfError(error: Throwable) {
    when (error) {
        is NoConnectivityException -> {
            Toasty.error(applicationContext, "Check your network connection", Toast.LENGTH_SHORT, true).show()
        }
        else -> {
            Toasty.error(applicationContext, "Unknown error", Toast.LENGTH_SHORT, true).show()
        }
    }
    simpleSwipeRefreshLayout.isRefreshing = false

}
fun MainActivity.refheshView() {
    rv_images.adapter.notifyDataSetChanged()
}