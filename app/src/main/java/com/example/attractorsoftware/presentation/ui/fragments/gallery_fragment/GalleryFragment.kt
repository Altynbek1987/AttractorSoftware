package com.example.attractorsoftware.presentation.ui.fragments.gallery_fragment

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.attractorsoftware.R
import com.example.attractorsoftware.databinding.FragmentGalleryBinding
import com.example.attractorsoftware.presentation.ui.fragments.gallery_fragment.adapter.GalleryAdapter
import com.example.attractorsoftware.utils.MediaResourceManager
import com.example.attractorsoftware.utils.hasPermissionCheckAndRequest


class GalleryFragment : Fragment() {
    private var binding: FragmentGalleryBinding? = null
    private var galleryAdapter: GalleryAdapter? = null
    private val viewModel by viewModels<GalleryViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initilization()
        setupGallery()
        setupSubscribe()
    }

    private fun initilization() {
        galleryAdapter = GalleryAdapter()
        binding?.recyclerViewGallery?.adapter = galleryAdapter
    }

    private fun setupGallery() {
        if (hasPermissionCheckAndRequest(
                requestPermissionLauncher,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
            )
        ) {
            lifecycleScope.launchWhenStarted {
                val mediaResourceManager = MediaResourceManager(requireContext())
                viewModel.retrieveMedia(mediaResourceManager)
            }
        }
    }

    private fun setupSubscribe() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.allMediaState.collect {
                galleryAdapter?.setList(it.list)
            }
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { isGranted ->
        for (permission in isGranted) {
            when {
                permission.value -> setupGallery()
                !shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE) -> {
                    getString(R.string.permission_denied)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
        galleryAdapter = null
    }
}















