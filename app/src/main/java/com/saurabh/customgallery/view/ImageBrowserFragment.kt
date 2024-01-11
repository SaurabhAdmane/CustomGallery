package com.saurabh.customgallery.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.saurabh.customgallery.R
import com.saurabh.customgallery.databinding.FragmentImageBrowserBinding
import com.saurabh.customgallery.model.ImageFiles

class ImageBrowserFragment(images: List<ImageFiles>, selectedImgPos: Int) :
    Fragment(R.layout.fragment_image_browser) {

    companion object {
        @JvmStatic
        fun newInstance(images: List<ImageFiles>, selectedImgPos: Int): ImageBrowserFragment {
            return ImageBrowserFragment(images, selectedImgPos)
        }
    }

    private var allImages = ArrayList<ImageFiles>()
    private var position: Int
    lateinit var binding: FragmentImageBrowserBinding

    init {
        allImages.addAll(images)
        position = selectedImgPos
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentImageBrowserBinding.inflate(inflater, container, false)
        binding.toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
        val imageViewPagerAdapter = ImageViewPagerAdapter(allImages)
        binding.viewPager.adapter = imageViewPagerAdapter
        binding.viewPager.setCurrentItem(position, true)
        return binding.root
    }
}