package com.skillbox.github.ui.user_info

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skillbox.github.data.UserInfo
import com.skillbox.github.data.UserRepository
import kotlinx.coroutines.*

class UserViewModel : ViewModel() {

    private val repository = UserRepository()
    private val userListLiveData = MutableLiveData<List<UserInfo.CurrentUser>>()
    private val reposListLiveData = MutableLiveData<List<UserInfo.ReposUser>>()
    private val infoStarredLiveData = MutableLiveData<Boolean>()
    private val putStarredLiveData = MutableLiveData<Boolean>()
    private val deleteStarredLiveData = MutableLiveData<Boolean>()
    private val followUserLiveData = MutableLiveData<List<UserInfo.UserFollow>>()

    val userList: LiveData<List<UserInfo.CurrentUser>>
        get() = userListLiveData

    val reposList: LiveData<List<UserInfo.ReposUser>>
        get() = reposListLiveData

    val infoStarred: LiveData<Boolean>
        get() = infoStarredLiveData

    val putStarred: LiveData<Boolean>
        get() = putStarredLiveData

    val delStarred: LiveData<Boolean>
        get() = deleteStarredLiveData

    val followUser: LiveData<List<UserInfo.UserFollow>>
        get() = followUserLiveData

    fun getUser() {
        viewModelScope.launch {
            try {
                val users = repository.asyncInfoFun()
                userListLiveData.postValue(users.filterIsInstance<UserInfo.CurrentUser>())
                followUserLiveData.postValue(users.filterIsInstance<UserInfo.UserFollow>())
            } catch (t: Throwable) {
                Log.d("ViewModel", "Error response", t)
                userListLiveData.postValue(emptyList())
                followUserLiveData.postValue(emptyList())
            }
        }
    }

    private val job = SupervisorJob()
    private val coroutine = CoroutineScope(Dispatchers.Main + job)

    fun getRepos() {
        coroutine.launch {
            try {
                val repos = repository.reposInfo()
                reposListLiveData.postValue(repos)
            } catch (t: Throwable) {
                Log.d("ViewModel", "Error response", t)
                reposListLiveData.postValue(emptyList())
            }
        }
    }

    fun infoStarredFun(nameUser: String, nameRepo: String) {
        viewModelScope.launch {
            try {
                val infoStar = repository.infoStarred(nameUser, nameRepo)
                infoStarredLiveData.postValue(infoStar)
            } catch (t: Throwable) {
                Log.d("ViewModel", "Error info a star", t)
            }
        }
    }

    fun putStar(nameUser: String, nameRepo: String) {
        viewModelScope.launch {
            try {
                val putStar = repository.putStar(nameUser, nameRepo)
                putStarredLiveData.postValue(putStar)
            } catch (t: Throwable) {
                Log.d("ViewModel", "Error put a star", t)
            }
        }
    }

    fun deleteStar(nameUser: String, nameRepo: String) {
        viewModelScope.launch {
            try {
                val delStar = repository.delStar(nameUser, nameRepo)
                deleteStarredLiveData.postValue(delStar)
            } catch (t: Throwable) {
                Log.d("ViewModel", "Error delete a star", t)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        coroutine.cancel()
    }
}