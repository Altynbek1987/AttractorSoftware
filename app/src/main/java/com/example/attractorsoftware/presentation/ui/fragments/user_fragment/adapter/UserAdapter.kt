package com.example.attractorsoftware.presentation.ui.fragments.user_fragment.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.attractorsoftware.data.models.PersonModel
import com.example.attractorsoftware.databinding.HolderUserBinding

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserHolder>() {
    private var list: ArrayList<PersonModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        return UserHolder(
            HolderUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }


    fun addList(arrayList: ArrayList<PersonModel>) {
        this.list.addAll(arrayList)
        notifyDataSetChanged()
    }

    inner class UserHolder(private var binding: HolderUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(userModel: PersonModel) {
            binding.photoUser.load(userModel.photo)
            binding.firstName.text = userModel.first_name
            binding.secondName.text = userModel.second_name
            binding.tvEducation.text = userModel.education
            userModel.company.forEach {
                if (it.name == "TEST_COMPANY") {
                    binding.nameCompany.text = it.name
                    binding.workUser.text = it.position
                } else {
                    binding.secondCompany.text = it.name
                    binding.secondWorkUser.text = it.position
                }
            }
        }
    }
}