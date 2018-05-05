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

class SpacePhotoActivity : AppCompatActivity(),ViewPager.OnPageChangeListener {


    var statusProgressBar = true
    lateinit var mResults: ArrayList<Result>
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_space_poster)
        fillProgressBar()
        mResults = intent.extras.getParcelableArrayList<Result>(resources.getString(R.string.id_array_intent))
        val position = intent.extras.getInt(resources.getString(R.string.id_position_intent))
        with(pager) {
            adapter = PosterPagerAdapter(this@SpacePhotoActivity, mResults)
            setCurrentItem(position, true)
            addOnPageChangeListener(this@SpacePhotoActivity)
        }

    }
    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {
        displayMetaInfo(position)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.space_poster_menu, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}

private fun SpacePhotoActivity.fillProgressBar() {
    setSupportActionBar(toolbar_space_photo_activity)
    supportActionBar!!.setDisplayShowTitleEnabled(false)
    supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    supportActionBar!!.setDisplayShowHomeEnabled(true)
}




fun SpacePhotoActivity.hideorShowProgressBar() {
    statusProgressBar = if (statusProgressBar) {
        supportActionBar!!.hide()
        false
    } else {
        supportActionBar!!.show()
        true
    }
    //toolbar_space_photo_activity.animate().translationY((-toolbar_space_photo_activity.height).toFloat()).interpolator = LinearInterpolator()
}

fun SpacePhotoActivity.displayMetaInfo(position: Int) {
    toolbar_space_photo_activity.position_poster.text = resources.getString(R.string.position_of_detail_view, position + 1, mResults.size)
}