package com.example.attractorsoftware.presentation.ui.fragments.user_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.attractorsoftware.data.models.PersonModel
import com.example.attractorsoftware.databinding.FragmentUserBinding
import com.example.attractorsoftware.presentation.ui.fragments.user_fragment.adapter.UserAdapter
import kotlinx.coroutines.flow.collectLatest

class UserFragment : Fragment() {
    private var binding: FragmentUserBinding? = null
    private val viewModel: UserViewModel by viewModels()
    private var userAdapter: UserAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupRequest()
        setupData()
    }

    private fun initialize() {
        userAdapter = UserAdapter()
        binding?.recyclerViewUser?.adapter = userAdapter
    }

    private fun setupRequest() {
        lifecycleScope.launchWhenStarted {
            viewModel.getUserData(requireContext())
        }
    }

    private fun setupData() {
        lifecycleScope.launchWhenStarted {
            viewModel.message.collectLatest { list ->
                userAdapter?.addList(list as ArrayList<PersonModel>)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        userAdapter = null
    }
}