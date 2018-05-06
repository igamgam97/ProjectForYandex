package com.example.gamgam.applicationforyandex.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.gamgam.applicationforyandex.R
import com.example.gamgam.applicationforyandex.models.Result

class PosterGalleryAdapter(private val mResults: ArrayList<Result>, private val listener: (View, Int) -> Unit) : RecyclerView.Adapter<PosterGalleryAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_poster, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
            = holder.bind(mResults[position], position,listener)

    override fun getItemCount() = mResults.size


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView = itemView.findViewById<ImageView>(R.id.iv_poster)
        fun bind(mResult: Result, position: Int, listener:(View, Int)->Unit) {
            Glide.with(itemView.context)
                    .load(mResult.urlSmallPoster)
                    .placeholder(R.drawable.placeholder)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView)

            imageView.setOnClickListener {
                listener(itemView,position)

            }

        }
    }
}