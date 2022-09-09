package com.skillbox.github.ui.repository_list

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.skillbox.github.R
import com.skillbox.github.ui.user_info.UserViewModel
import kotlinx.android.synthetic.main.fragment_info_repos.*

class InfoReposFragment : Fragment(R.layout.fragment_info_repos) {

    private val userViewModel: UserViewModel by viewModels()
    private val args: InfoReposFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userViewModel.infoStarredFun(args.nameUser, args.nameRepo)
        starredButton.setOnClickListener {
            clickListener()
        }
        observeViewModel()
    }

    @SuppressLint("SetTextI18n")
    private fun observeViewModel() {
        userViewModel.infoStarred.observe(viewLifecycleOwner) { star ->
            resultVerification(star)
        }
        userViewModel.putStarred.observe(viewLifecycleOwner) { result ->
            putDelStar(result)
        }
        userViewModel.delStarred.observe(viewLifecycleOwner) { result ->
            putDelStar(!result)
        }
    }

    private fun clickListener() {
        when (userViewModel.infoStarred.value) {
            true -> userViewModel.deleteStar(args.nameUser, args.nameRepo)
            false -> userViewModel.putStar(args.nameUser, args.nameRepo)
        }
    }

    private fun resultVerification(star: Boolean) {
        if (star) {
            starredTextView.text = getString(R.string.star_set)
            starredButton.text = getString(R.string.remove_star)
        } else {
            starredTextView.text = getString(R.string.star_not_set)
            starredButton.text = getString(R.string.put_star)
        }
    }

    private fun putDelStar(result: Boolean) {
        if (result) {
            starredTextView.text = getString(R.string.star_set)
            starredButton.text = getString(R.string.remove_star)
        } else {
            starredTextView.text = getString(R.string.star_not_set)
            starredButton.text = getString(R.string.put_star)
        }
    }
}