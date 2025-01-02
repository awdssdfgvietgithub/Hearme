package com.app.hearme.data.util

import android.app.Activity
import android.content.Context
import com.app.hearme.R

class UserManager private constructor() {

    private var userId: Int? = null
        private set

    fun setUserId(id: Int) {
        this.userId = id
    }

    fun getUserId(): Int = userId ?: -1

    private var username: String? = null
        private set

    fun setUserName(username: String) {
        this.username = username
    }

    fun getUserName(): String = username ?: ""

    private var avatar: String? = null
        private set

    fun setAvatar(avatar: String) {
        this.avatar = avatar
    }

    fun getAvatar(): String = avatar ?: ""

    private var displayName: String? = null
        private set

    fun setDisplayName(displayName: String) {
        this.displayName = displayName
    }

    fun getDisplayName(): String = displayName ?: ""

    fun clearData() {
        setUserId(-1)
        setUserName("")
        setAvatar("")
        setDisplayName("")
    }

    companion object {
        @Volatile
        private var instance: UserManager? = null

        fun getInstance(): UserManager {
            return instance ?: synchronized(this) {
                instance ?: UserManager().also { instance = it }
            }
        }
    }
}
