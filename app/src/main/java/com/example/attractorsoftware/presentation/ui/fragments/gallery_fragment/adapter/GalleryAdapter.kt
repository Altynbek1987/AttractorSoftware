package com.example.attractorsoftware.presentation.ui.fragments.gallery_fragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.attractorsoftware.data.models.GalleryModel
import com.example.attractorsoftware.databinding.HolderGalleryBinding

class GalleryAdapter : RecyclerView.Adapter<GalleryAdapter.GalleryHolder>() {
    private var list: ArrayList<GalleryModel> = ArrayList()

    fun setList(list: ArrayList<GalleryModel>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GalleryAdapter.GalleryHolder {
        return GalleryHolder(
            HolderGalleryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: GalleryAdapter.GalleryHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class GalleryHolder(private var binding: HolderGalleryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(model: GalleryModel) {
            binding.imGallery.load(model.contentUrl)
        }

    }
}