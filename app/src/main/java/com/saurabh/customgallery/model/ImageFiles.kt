package com.saurabh.customgallery.model

data class ImageFiles(
    var imageName: String = "",
    var imagePath: String = "",
    var imageSize: String = "",
    var imageUri: String = "",
    var selected: Boolean = false
)