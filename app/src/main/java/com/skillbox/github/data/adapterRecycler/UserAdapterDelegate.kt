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
import kotlinx.android.synthetic.main.user_item.*
import kotlin.random.Random

class UserAdapterDelegate :
    AbsListItemAdapterDelegate<UserInfo.CurrentUser, UserInfo, UserAdapterDelegate.UserHolder>() {

    override fun isForViewType(
        item: UserInfo,
        items: MutableList<UserInfo>,
        position: Int
    ): Boolean {
        return item is UserInfo.CurrentUser
    }

    override fun onCreateViewHolder(parent: ViewGroup): UserHolder {
        return UserHolder(
            parent.inflate(R.layout.user_item)
        )
    }

    override fun onBindViewHolder(
        item: UserInfo.CurrentUser,
        holder: UserHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    abstract class BaseUserHolder(
        final override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        @SuppressLint("SetTextI18n")
        protected fun bindMainInfo(
            id: Long,
            name: String,
            avatar: String,
            typeUser: String,
            quantityRepo: Int
        ) {
            idUser.text = "Id user: $id"
            nameUser.text = name
            typeUserView.text = "Type user: $typeUser"
            repoUser.text = "Quantity repo user: $quantityRepo"

            Glide.with(itemView)
                .load(avatar)
                .placeholder(R.drawable.ic_portrait)
                .into(avatarImageView)
        }
    }

    class UserHolder(containerView: View) : BaseUserHolder(containerView) {
        fun bind(user: UserInfo.CurrentUser) {
            bindMainInfo(user.id, user.name, user.avatar, user.typeUser, user.quantityRepo)
        }
    }
}