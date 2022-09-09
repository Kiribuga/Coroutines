package com.skillbox.github.data.adapterRecycler

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.skillbox.github.R
import com.skillbox.github.data.UserInfo
import com.skillbox.github.utils.inflate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.repo_item.*

class ReposAdapterDelegate(
    private val onItemClick: (userName: String, repoName: String) -> Unit
) : AbsListItemAdapterDelegate<UserInfo.ReposUser, UserInfo, ReposAdapterDelegate.ReposHolder>() {

    override fun isForViewType(
        item: UserInfo,
        items: MutableList<UserInfo>,
        position: Int
    ): Boolean {
        return item is UserInfo.ReposUser
    }

    override fun onCreateViewHolder(parent: ViewGroup): ReposHolder {
        return ReposHolder(
            parent.inflate(R.layout.repo_item),
            onItemClick
        )
    }

    override fun onBindViewHolder(
        item: UserInfo.ReposUser,
        holder: ReposHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    abstract class BaseReposHolder(
        final override val containerView: View,
        onItemClick: (userName: String, repoName: String) -> Unit
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        init {
            containerView.setOnClickListener {
                onItemClick(userName.orEmpty(), repoName.orEmpty())
            }
        }

        private var userName: String? = null
        private var repoName: String? = null

        @SuppressLint("SetTextI18n")
        protected fun bindMainInfo(
            owner: String,
            idRepos: Long,
            name: String
        ) {
            userName = owner
            idRepo.text = "Id repo: $idRepos"
            repoName = name
            userOwner.text = owner
            nameRepo.text = name
        }
    }

    class ReposHolder(
        containerView: View,
        onItemClick: (userName: String, repoName: String) -> Unit
    ) : BaseReposHolder(containerView, onItemClick) {
        fun bind(repos: UserInfo.ReposUser) {
            bindMainInfo(repos.userName, repos.idRepo, repos.nameRepo)
        }
    }
}
