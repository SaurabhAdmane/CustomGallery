package com.saurabh.customgallery.dataprovider

import android.database.Cursor
import android.provider.MediaStore
import com.saurabh.customgallery.MyApplication
import com.saurabh.customgallery.model.ImageFiles
import com.saurabh.customgallery.model.ImageFolders

class ImageGalleryDataProvider : ImageGalleryProvider {

    override suspend fun getFoldersFromStorage(): ArrayList<ImageFolders> {
        val imageFolders = arrayListOf<ImageFolders>()
        val picPaths = arrayListOf<String>()
        val allImagesuri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(
            MediaStore.Images.ImageColumns.DATA,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Images.Media.BUCKET_ID
        )
        val cursor: Cursor = MyApplication.getContext()!!.contentResolver.query(
            allImagesuri, projection, null, null, null
        )!!
        try {
            while (cursor.moveToNext()) {
                val folds = ImageFolders()
                val folder =
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME))
                val dataPath =
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))

                var folderpaths = dataPath.substring(0, dataPath.lastIndexOf("$folder/"))
                folderpaths = "$folderpaths$folder/"
                if (!picPaths.contains(folderpaths)) {
                    picPaths.add(folderpaths)
                    folds.imagePath = folderpaths
                    folds.folderName = folder
                    folds.firstPic = dataPath
                    folds.totalImages += 1
                    imageFolders.add(folds)
                } else {
                    for (i in imageFolders.indices) {
                        if (imageFolders[i].imagePath == folderpaths) {
                            imageFolders[i].firstPic = dataPath
                            imageFolders[i].totalImages += 1
                        }
                    }
                }
            }
            cursor.close()
        } catch (e: Exception) {
            e.printStackTrace()
            cursor.close()
        }
        return imageFolders
    }

    override suspend fun getImagesFromFolder(path: String): ArrayList<ImageFiles> {
        var images = ArrayList<ImageFiles>()
        val allVideosuri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(
            MediaStore.Images.ImageColumns.DATA,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.SIZE
        )
        val cursor = MyApplication.getContext()!!.contentResolver.query(
            allVideosuri,
            projection,
            MediaStore.Images.Media.DATA + " like ? ",
            arrayOf("%$path%"),
            null
        )!!
        try {
            while (cursor.moveToNext()) {
                val pic = ImageFiles()
                pic.imageName =
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME))
                pic.imagePath =
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
                pic.imageSize =
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE))
                images.add(pic)
            }
            cursor.close()
            val reSelection = ArrayList<ImageFiles>()
            images.forEach { it ->
                reSelection.add(it)
            }
            images = reSelection
        } catch (e: Exception) {
            e.printStackTrace()
            cursor.close()
        }
        return images
    }
}