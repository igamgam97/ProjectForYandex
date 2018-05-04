package com.example.gamgam.applicationforyandex.activity


import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import com.example.gamgam.applicationforyandex.R
import com.example.gamgam.applicationforyandex.adapters.PosterPagerAdapter
import com.example.gamgam.applicationforyandex.models.Result
import kotlinx.android.synthetic.main.activity_space_poster.*
import kotlinx.android.synthetic.main.activity_space_poster.view.*

class SpacePhotoActivity : AppCompatActivity() {
    var statusProgressBar=true
    lateinit var mResults:ArrayList<Result>
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_space_poster)
        fillProgressBar()
        mResults = intent.extras.getParcelableArrayList<Result>(resources.getString(R.string.id_array_intent))
        val position=intent.extras.getInt(resources.getString(R.string.id_position_intent))
        val adapter = PosterPagerAdapter(this, mResults)
        pager.adapter=adapter
        pager.setCurrentItem(position,true)
        displayMetaInfo(position)
        pager.addOnPageChangeListener(getOnPageChageListerner())

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.space_poster_menu,menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}

private fun SpacePhotoActivity.fillProgressBar(){
    setSupportActionBar(toolbar_space_photo_activity)
    supportActionBar!!.setDisplayShowTitleEnabled(false)
    supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    supportActionBar!!.setDisplayShowHomeEnabled(true)
}

private fun SpacePhotoActivity.getOnPageChageListerner(): ViewPager.OnPageChangeListener {
    return object : ViewPager.OnPageChangeListener {

        override fun onPageSelected(position: Int) {
            displayMetaInfo(position)

        }

        override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {
        }

        override fun onPageScrollStateChanged(arg0: Int) {

        }
    }
}


fun SpacePhotoActivity.hideorShowProgressBar(){
    statusProgressBar = if(statusProgressBar){
        supportActionBar!!.hide()
        false
    }else{
        supportActionBar!!.show()
        true
    }
    //toolbar_space_photo_activity.animate().translationY((-toolbar_space_photo_activity.height).toFloat()).interpolator = LinearInterpolator()
}

fun SpacePhotoActivity.displayMetaInfo(position: Int) {
   toolbar_space_photo_activity.position_poster.text=resources.getString(R.string.position_of_detailview,position+1,mResults.size)
}