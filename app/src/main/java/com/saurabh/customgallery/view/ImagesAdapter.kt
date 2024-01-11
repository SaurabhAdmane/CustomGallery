package com.saurabh.customgallery.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.saurabh.customgallery.ClickListner
import com.saurabh.customgallery.MyApplication
import com.saurabh.customgallery.R
import com.saurabh.customgallery.model.ImageFiles

class ImagesAdapter(
    private val imagesList: List<ImageFiles>, private val clickListner: ClickListner
) : RecyclerView.Adapter<ImagesAdapter.ImageListAdapterViewHolder>() {

    private var inflater: LayoutInflater? = null

    override fun onBindViewHolder(holder: ImageListAdapterViewHolder, position: Int) {
        val image: ImageFiles = imagesList[position]

        MyApplication.getContext()?.let {
            Glide.with(it).load(image.imagePath).apply(RequestOptions().centerCrop())
                .into(holder.img)
        }


        holder.cardView.setOnClickListener(View.OnClickListener {
            clickListner.onImageItemClicked(imagesList, position)
        })
    }

    class ImageListAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val img = view.findViewById<AppCompatImageView>(R.id.img)
        val cardView = view.findViewById<CardView>(R.id.img_cardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageListAdapterViewHolder {
        if (inflater == null) inflater = LayoutInflater.from(parent.context)

        return ImageListAdapterViewHolder(
            inflater?.inflate(
                R.layout.item_images, parent, false
            )!!
        )
    }

    override fun getItemCount(): Int {
        return imagesList.size
    }
}