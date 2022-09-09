package com.skillbox.github.data.adapterRecycler

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.skillbox.github.data.UserInfo

class AdapterUser(
    private val onItemClick: (userName: String, repoName: String) -> Unit
) : AsyncListDifferDelegationAdapter<UserInfo>(UserDiff()) {

    init {
        delegatesManager.addDelegate(UserAdapterDelegate())
            .addDelegate(ReposAdapterDelegate(onItemClick))
    }

    class UserDiff : DiffUtil.ItemCallback<UserInfo>() {
        override fun areItemsTheSame(oldItem: UserInfo, newItem: UserInfo): Boolean {
            return when {
                oldItem is UserInfo.CurrentUser && newItem is UserInfo.CurrentUser -> oldItem.id == newItem.id
                oldItem is UserInfo.ReposUser && newItem is UserInfo.ReposUser -> oldItem.idRepo == newItem.idRepo
                else -> false
            }
        }

        override fun areContentsTheSame(oldItem: UserInfo, newItem: UserInfo): Boolean {
            return oldItem == newItem
        }
    }
}