package com.example.gamgam.applicationforyandex


import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.ProgressBar
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.github.chrisbanes.photoview.PhotoView
import java.lang.Exception


class SpacePhotoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_detail)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        val mImageView = findViewById<PhotoView>(R.id.image)
        val imageSpace = intent.getParcelableExtra<ImageSpace>(EXTRA_SPACE_PHOTO)
        val  progressBar = findViewById<ProgressBar>(R.id.progress)

        Glide.with(this)
                .load(imageSpace.url)
                .asBitmap()
                .listener(object : RequestListener<String, Bitmap>{
                    override fun onException(e: Exception?, model: String?, target: Target<Bitmap>?, isFirstResource: Boolean): Boolean {
                        progressBar.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(resource: Bitmap?, model: String?, target: Target<Bitmap>?, isFromMemoryCache: Boolean, isFirstResource: Boolean): Boolean {
                        progressBar.visibility = View.GONE
                        return false
                    }

                })
                .placeholder(R.drawable.ic_preview_image)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(mImageView)

    }

    companion object {

        val EXTRA_SPACE_PHOTO = "SpacePhotoActivity.SPACE_PHOTO"
    }
}
