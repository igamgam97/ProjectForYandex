package com.example.gamgam.applicationforyandex


import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import kotlinx.android.synthetic.main.activity_space_photo.*

class SpacePhotoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_space_photo)
        fillProgressBar()
        //window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        val adapter = MyViewPagerAdapter(this,ImageSpace.getSpacePhotos())
        pager.adapter=adapter
        pager.setCurrentItem(2,true)
        displayMetaInfo(2)
        pager.addOnPageChangeListener(getOnPageChageListerner())


    }

    companion object {

        val EXTRA_SPACE_PHOTO = "SpacePhotoActivity.SPACE_PHOTO"
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.space_photo_menu,menu)
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

private fun SpacePhotoActivity.displayMetaInfo(position: Int) {
    position_poster.text =resources.getString(R.string.position_of_detailview,position+1,ImageSpace.getSpacePhotos().size)
}