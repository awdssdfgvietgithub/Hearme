package com.app.hearme.network.models

import com.google.gson.annotations.SerializedName
import com.app.hearme.model.User
import com.app.hearme.network.BaseResponse

data class SearchUserResponse(
    @SerializedName("users") val users: MutableList<UserModel> = mutableListOf()
) : BaseResponse() {
    data class UserModel(
        @SerializedName("avatar") val avatar: String = "",
        @SerializedName("display_name") val displayName: String = "",
        @SerializedName("followers") val followers: Int = 0,
        @SerializedName("following") val following: Int = 0,
        @SerializedName("id") val id: Int = -1,
        @SerializedName("normal_display_name") val normalDisplayName: String = "",
        @SerializedName("username") val username: String = "",
    )
}

fun SearchUserResponse.UserModel.toUser(): User {
    val dto = User(
        userId = id,
        email = "",
        password = "",
        avatar = null,
        avatarUrl = avatar,
        fullName = displayName,
        followersCount = followers,
        followingCount = following,
    )

    return dto
}
