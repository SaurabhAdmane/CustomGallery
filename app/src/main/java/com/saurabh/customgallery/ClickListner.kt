package com.saurabh.customgallery

import com.saurabh.customgallery.model.ImageFiles

interface ClickListner {
    fun onItemClicked(pictureFolderPath: String, folderName: String)
    fun onImageItemClicked(imageLists: List<ImageFiles>, position: Int)

}