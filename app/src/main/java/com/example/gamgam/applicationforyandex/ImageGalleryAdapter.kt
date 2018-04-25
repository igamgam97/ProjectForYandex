package com.example.gamgam.applicationforyandex

import android.content.Context
import android.support.v4.content.ContextCompat.startActivity
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide


class ImageGalleryAdapter(private val context: Context, private val mSpacePhotos: Array<ImageSpace>) : RecyclerView.Adapter<ImageGalleryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageGalleryAdapter.ViewHolder {
        val photoView= LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
        return ViewHolder(photoView)
    }

    override fun onBindViewHolder(holder: ImageGalleryAdapter.ViewHolder, position: Int) {

        val spacePhoto = mSpacePhotos[position]
        val imageView = holder.myImageView
        Glide.with(context)
                .load(spacePhoto.url)
                .placeholder(R.drawable.ic_preview_image)
                .into(imageView)

        imageView.setOnClickListener {
            if (position != RecyclerView.NO_POSITION) {
                val intent = Intent(context, SpacePhotoActivity::class.java)
                intent.putExtra(SpacePhotoActivity.EXTRA_SPACE_PHOTO, spacePhoto)
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount()=mSpacePhotos.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val myImageView = itemView.findViewById<ImageView>(R.id.iv_photo)!!



    }
}