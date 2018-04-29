package com.example.gamgam.applicationforyandex

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


class ImageGalleryAdapter(private val mSpacePhotos: Array<ImageSpace>,private val listener: (View,Int,ImageSpace) -> Unit) : RecyclerView.Adapter<ImageGalleryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false))

    override fun onBindViewHolder(holder: ImageGalleryAdapter.ViewHolder, position: Int)
            = holder.bind(mSpacePhotos[position], position,listener)

    override fun getItemCount() = mSpacePhotos.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView = itemView.findViewById<ImageView>(R.id.iv_photo)!!

        fun bind(spacePhoto: ImageSpace, position: Int,listener:(View,Int,ImageSpace)->Unit) {
            Glide.with(itemView.context)
                    .load(spacePhoto.url)
                    .placeholder(R.drawable.ic_preview_image)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView)

            imageView.setOnClickListener {
               listener(itemView,position,spacePhoto)

            }

        }
    }
}