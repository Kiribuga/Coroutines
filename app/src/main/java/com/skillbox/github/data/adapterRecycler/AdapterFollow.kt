package com.skillbox.github.data.adapterRecycler

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.skillbox.github.data.UserInfo

class AdapterFollow:
    AsyncListDifferDelegationAdapter<UserInfo.UserFollow>(FollowDiffUtilCallback()) {

    init {
        delegatesManager.addDelegate(FollowAdapterDelegate())
    }

    class FollowDiffUtilCallback : DiffUtil.ItemCallback<UserInfo.UserFollow>() {
        override fun areItemsTheSame(
            oldItem: UserInfo.UserFollow,
            newItem: UserInfo.UserFollow
        ): Boolean {
            return oldItem.idFollow == newItem.idFollow
        }

        override fun areContentsTheSame(
            oldItem: UserInfo.UserFollow,
            newItem: UserInfo.UserFollow
        ): Boolean {
            return oldItem == newItem
        }
    }
}