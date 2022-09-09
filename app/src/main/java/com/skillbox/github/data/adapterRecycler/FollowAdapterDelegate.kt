package com.skillbox.github.data.adapterRecycler

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.skillbox.github.R
import com.skillbox.github.data.UserInfo
import com.skillbox.github.utils.inflate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.follow_item.*

class FollowAdapterDelegate :
    AbsListItemAdapterDelegate<UserInfo.UserFollow, UserInfo.UserFollow, FollowAdapterDelegate.FollowHolder>() {

    override fun isForViewType(
        item: UserInfo.UserFollow,
        items: MutableList<UserInfo.UserFollow>,
        position: Int
    ): Boolean {
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup): FollowHolder {
        return FollowHolder(
            parent.inflate(R.layout.follow_item)
        )
    }

    override fun onBindViewHolder(
        item: UserInfo.UserFollow,
        holder: FollowHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    abstract class BaseFollowHolder(
        final override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        @SuppressLint("SetTextI18n")
        protected fun bindMainInfo(
            id: Long,
            nameFollow: String,
            avatarFollow: String
        ) {
            followId.text = "Id user: $id"
            followName.text = nameFollow

            Glide.with(itemView)
                .load(avatarFollow)
                .placeholder(R.drawable.ic_portrait)
                .into(followAvatar)
        }
    }

    class FollowHolder(containerView: View) : BaseFollowHolder(containerView) {
        fun bind(followUser: UserInfo.UserFollow) {
            bindMainInfo(followUser.idFollow, followUser.nameFollow, followUser.avatarFollow)
        }
    }
}