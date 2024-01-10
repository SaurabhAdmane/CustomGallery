package com.saurabh.customgallery.dataprovider

import com.saurabh.customgallery.model.ImageFiles
import com.saurabh.customgallery.model.ImageFolders

interface ImageGalleryProvider {
    suspend fun getFoldersFromStorage(): ArrayList<ImageFolders>

    suspend fun getImagesFromFolder(path: String): ArrayList<ImageFiles>
}