package com.example.gamgam.applicationforyandex

import android.content.Context
import android.graphics.Bitmap
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.github.chrisbanes.photoview.PhotoView
import java.lang.Exception


class MyViewPagerAdapter(private val spacePhotoActivity: SpacePhotoActivity,private val images:Array<ImageSpace>) : PagerAdapter() {
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === (`object` as View)
    }

    override fun getCount(): Int {
        return images.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View )
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val context = spacePhotoActivity as Context
        val view = LayoutInflater.from(context).inflate(R.layout.slider_image,container,false)
        val phootoView =view.findViewById<PhotoView>(R.id.image)
        val  progressBar =view.findViewById<ProgressBar>(R.id.progress)
        val imageSpace = images[position]

        Glide.with(context)
                .load(imageSpace.url)
                .asBitmap()
                .listener(object : RequestListener<String, Bitmap> {
                    override fun onException(e: Exception?, model: String?, target: Target<Bitmap>?, isFirstResource: Boolean): Boolean {
                        progressBar.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(resource: Bitmap?, model: String?, target: Target<Bitmap>?, isFromMemoryCache: Boolean, isFirstResource: Boolean): Boolean {
                        progressBar.visibility = View.GONE
                        return false
                    }


                })
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(phootoView)

        container.addView(view)
        return view
    }

}
