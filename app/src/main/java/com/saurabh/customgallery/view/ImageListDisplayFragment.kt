package com.saurabh.customgallery.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.saurabh.customgallery.ClickListner
import com.saurabh.customgallery.R
import com.saurabh.customgallery.databinding.FragmentImageListBinding
import com.saurabh.customgallery.viewmodel.ImageGallerySharedViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class ImageListDisplayFragment : Fragment(R.layout.fragment_image_list), ClickListner {

    lateinit var binding: FragmentImageListBinding
    private var viewModel: ImageGallerySharedViewModel? = null
    private var folderPath = ""
    private var folderName = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentImageListBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(ImageGallerySharedViewModel::class.java)
        getArgs()
        binding.toolbar.title = folderName
        showImages()
        return binding.root
    }

    private fun showImages() {
        lifecycleScope.launch {
            viewModel!!.allImagesFormFolder.collectLatest {
                it.let { data ->
                    val layoutManager = GridLayoutManager(requireContext(), 2)
                    binding.recyclerImageList.setLayoutManager(layoutManager)
                    binding.recyclerImageList.adapter =
                        ImagesAdapter(data, this@ImageListDisplayFragment)
                }
            }
        }
    }

    private fun getArgs() {
        arguments?.let {
            folderPath = it.getString("folder_path", "")
            folderName = it.getString("folder_name", "")
        }
        viewModel!!.getImagesFromFolder(folderPath)
    }

    override fun onItemClicked(pictureFolderPath: String, folderName: String) {

    }
}