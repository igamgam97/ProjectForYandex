package com.example.gamgam.applicationforyandex

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

class PosterPagerAdapter(private val spacePhotoActivity: SpacePhotoActivity,private val images:ArrayList<Result>) : PagerAdapter() {
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getCount(): Int {
        return images.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context).inflate(R.layout.slider_image,container,false)
        val phootoView =view.findViewById<PhotoView>(R.id.image)
        val  progressBar =view.findViewById<ProgressBar>(R.id.progress)
        val imageSpace = images[position]

        Glide.with(container.context)
                .load(imageSpace.urlBigPoster)
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
                .error(R.drawable.logo_image)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(phootoView)
                phootoView.setOnClickListener{
                    spacePhotoActivity.hideorShowProgressBar()
                }

        container.addView(view)
        return view
    }

}