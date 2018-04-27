package com.example.gamgam.applicationforyandex


import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import kotlinx.android.synthetic.main.activity_phot_detail2.*

class SpacePhotoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phot_detail2)
        val toolbar=findViewById<Toolbar>(R.id.toolbar2)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        //window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        val mPager = findViewById<ViewPager>(R.id.pager)
        val adapter = MyViewPagerAdapter(this,ImageSpace.getSpacePhotos())
        mPager.adapter=adapter
        mPager.setCurrentItem(2,true)
        displayMetaInfo(2)
        val viewPagerPageChangeListener = object : ViewPager.OnPageChangeListener {

            override fun onPageSelected(position: Int) {
                displayMetaInfo(position)

            }

            override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {
            }

            override fun onPageScrollStateChanged(arg0: Int) {

            }
        }
        mPager.addOnPageChangeListener(viewPagerPageChangeListener)


    }

    companion object {

        val EXTRA_SPACE_PHOTO = "SpacePhotoActivity.SPACE_PHOTO"
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_info_menu,menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun displayMetaInfo(position: Int) {
        name_film.text =resources.getString(R.string.position_of_detailview,position+1,ImageSpace.getSpacePhotos().size)
    }
}
