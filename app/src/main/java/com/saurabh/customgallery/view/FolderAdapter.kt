package com.saurabh.customgallery.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.saurabh.customgallery.ClickListner
import com.saurabh.customgallery.MyApplication
import com.saurabh.customgallery.R
import com.saurabh.customgallery.model.ImageFolders

class FolderAdapter(private val folders: List<ImageFolders>, private val clickListner: ClickListner) :
    RecyclerView.Adapter<FolderAdapter.FolderListAdapterViewHolder>() {

    private var inflater: LayoutInflater? = null

    override fun onBindViewHolder(holder: FolderListAdapterViewHolder, position: Int) {
        val folder: ImageFolders = folders[position]

        MyApplication.getContext()?.let {
            Glide.with(it)
                .load(folder.firstPic)
                .apply(RequestOptions().centerCrop())
                .into(holder.imgThumbnail)
        }

        //setting the number of images

        //setting the number of images
        val text = "" + folder.folderName
        val folderSizeString = "" + folder.totalImages + " Media"
        holder.txtFolderSize.text = folderSizeString
        holder.txtFolderName.text = text

        holder.cardView.setOnClickListener(View.OnClickListener {
            clickListner.onItemClicked(folder.imagePath, folder.folderName)
        })
    }

    class FolderListAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtFolderName = view.findViewById<TextView>(R.id.txt_folder_name)
        val txtFolderSize = view.findViewById<TextView>(R.id.txt_folder_size)
        val imgThumbnail = view.findViewById<ImageView>(R.id.img_thumb)
        val cardView = view.findViewById<CardView>(R.id.cardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FolderListAdapterViewHolder {
        if (inflater == null) inflater = LayoutInflater.from(parent.context)

        return FolderListAdapterViewHolder(
            inflater?.inflate(
                R.layout.item_folder, parent, false
            )!!
        )
    }

    override fun getItemCount(): Int {
        return folders.size
    }
}