package com.skillbox.github.ui.repository_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.skillbox.github.R
import com.skillbox.github.data.adapterRecycler.AdapterUser
import com.skillbox.github.ui.user_info.UserViewModel
import com.skillbox.github.utils.autoCleared
import kotlinx.android.synthetic.main.fragment_repository_list.*
import kotlinx.android.synthetic.main.repo_item.*

class RepositoryListFragment : Fragment(R.layout.fragment_repository_list) {

    private var adapterRepos: AdapterUser by autoCleared()
    private val userViewModel: UserViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        initViewModel()
    }

    private fun initViewModel() {
        userViewModel.getRepos()
        userViewModel.reposList.observe(viewLifecycleOwner) { repos ->
            adapterRepos.items = repos
        }
    }

    private fun initList() {
        adapterRepos = AdapterUser { nameUser, nameRepo ->
            val action = RepositoryListFragmentDirections
                .actionRepositoryListFragmentToInfoReposFragment(
                    nameUser,
                    nameRepo
                )
            findNavController().navigate(action)
        }
        with(listRepoUser) {
            adapter = adapterRepos
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }
}