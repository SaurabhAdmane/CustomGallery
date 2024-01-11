package com.saurabh.customgallery.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.saurabh.customgallery.MyApplication
import com.saurabh.customgallery.databinding.ItemImageBrowseBinding
import com.saurabh.customgallery.model.ImageFiles

class ImageViewPagerAdapter(private val imagesList: ArrayList<ImageFiles>) :
    RecyclerView.Adapter<ImageViewPagerAdapter.ViewHolder>() {

    lateinit var binding: ItemImageBrowseBinding

    class ViewHolder(binding: ItemImageBrowseBinding) : RecyclerView.ViewHolder(binding.root) {
        var images: ImageView

        init {
            images = binding.imgBrowser
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemImageBrowseBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = imagesList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        MyApplication.getContext()?.let {
            Glide.with(it).load(imagesList[position].imagePath).into(holder.images)
        }
    }
}