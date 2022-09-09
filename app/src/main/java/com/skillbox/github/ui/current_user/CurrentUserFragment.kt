package com.skillbox.github.ui.current_user

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.skillbox.github.R
import com.skillbox.github.data.adapterRecycler.AdapterFollow
import com.skillbox.github.data.adapterRecycler.AdapterUser
import com.skillbox.github.ui.user_info.UserViewModel
import com.skillbox.github.utils.autoCleared
import kotlinx.android.synthetic.main.fragment_current_user.*

class CurrentUserFragment : Fragment(R.layout.fragment_current_user) {

    private var adapterUser: AdapterUser by autoCleared()
    private var adapterFollow: AdapterFollow by autoCleared()
    private val userViewModel: UserViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        initViewModel()
    }

    private fun initViewModel() {
        userViewModel.getUser()
        userViewModel.userList.observe(viewLifecycleOwner) { user ->
            adapterUser.items = user
        }
        userViewModel.followUser.observe(viewLifecycleOwner) { follow ->
            adapterFollow.items = follow
        }
    }

    private fun initList() {
        adapterUser = AdapterUser { _, _ -> }
        with(infoUser) {
            adapter = adapterUser
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
        adapterFollow = AdapterFollow()
        with(userFollow) {
            adapter = adapterFollow
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }
}