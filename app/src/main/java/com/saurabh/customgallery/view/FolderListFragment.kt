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
import com.saurabh.customgallery.databinding.FragmentFolderListBinding
import com.saurabh.customgallery.model.ImageFiles
import com.saurabh.customgallery.utility.CommonUtils
import com.saurabh.customgallery.viewmodel.ImageGallerySharedViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class FolderListFragment : Fragment(R.layout.fragment_folder_list), ClickListner {

    private var viewModel: ImageGallerySharedViewModel? = null
    private lateinit var binding: FragmentFolderListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentFolderListBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(ImageGallerySharedViewModel::class.java)

        viewModel!!.loadFolder()

        lifecycleScope.launch {
            viewModel!!.allFolderForGallery.collectLatest {
                it.let { data ->
                    if (data.isNotEmpty()) {
                        binding.txtNoImage.visibility = View.GONE
                        val layoutManager = GridLayoutManager(requireContext(), 2)
                        binding.recyclerFolderList.setLayoutManager(layoutManager)
                        binding.recyclerFolderList.adapter =
                            FolderAdapter(data, this@FolderListFragment)
                    }
                }
            }
        }

        return binding.root
    }

    override fun onItemClicked(folderPath: String, folderName: String) {
        val args = Bundle()
        args.putString("folder_path", folderPath)
        args.putString("folder_name", folderName)
        val fragment = ImageListDisplayFragment()
        fragment.arguments = args
        parentFragmentManager.let {
            CommonUtils.replaceFragment(
                it,
                fragment,
                R.id.fragment_container
            )
        }
    }

    override fun onImageItemClicked(imageLists: List<ImageFiles>, position: Int) {

    }
}