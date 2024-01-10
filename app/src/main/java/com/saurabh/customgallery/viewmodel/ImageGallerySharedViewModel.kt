package com.saurabh.customgallery.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saurabh.customgallery.dataprovider.ImageGalleryDataProvider
import com.saurabh.customgallery.model.ImageFiles
import com.saurabh.customgallery.model.ImageFolders
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ImageGallerySharedViewModel : ViewModel() {
    val allFolderForGallery: MutableStateFlow<List<ImageFolders>> =
        MutableStateFlow(listOf())
    val allImagesFormFolder: MutableStateFlow<List<ImageFiles>> =
        MutableStateFlow(listOf())
    val imageGalleryProvider = ImageGalleryDataProvider()

    fun loadFolder() {
        viewModelScope.launch {
            allFolderForGallery.value = withContext(Dispatchers.IO) {
                imageGalleryProvider.getFoldersFromStorage()
            }
        }
    }

    fun getImagesFromFolder(folderPath: String) {
        viewModelScope.launch {
            allImagesFormFolder.value = withContext(Dispatchers.IO){
                imageGalleryProvider.getImagesFromFolder(folderPath)
            }
        }
    }
}