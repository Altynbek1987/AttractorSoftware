package com.example.attractorsoftware.presentation.ui.fragments.gallery_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.attractorsoftware.data.models.GalleryList
import com.example.attractorsoftware.data.models.GalleryModel
import com.example.attractorsoftware.data.models.Mode
import com.example.attractorsoftware.utils.MediaResourceManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GalleryViewModel : ViewModel() {

    private val _allMediaState =
        MutableStateFlow<GalleryList<GalleryModel>>(GalleryList())
    val allMediaState: StateFlow<GalleryList<GalleryModel>> = _allMediaState.asStateFlow()

     fun retrieveMedia(mediaManager: MediaResourceManager) = viewModelScope.launch {
        _allMediaState.value =
            mediaManager.retrieveMedia(
                mode = Mode.All
            )
        val modelList = mediaManager.retrieveMedia(
            mode = Mode.All
        )
        if (modelList.list.isNotEmpty()) {
            _allMediaState.value = modelList
        }
    }
}