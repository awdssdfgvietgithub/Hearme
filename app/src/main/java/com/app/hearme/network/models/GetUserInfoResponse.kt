package com.app.hearme.network.models

import com.app.hearme.model.User
import com.google.gson.annotations.SerializedName
import com.app.hearme.network.BaseResponse

data class GetUserInfoResponse(
    @SerializedName("user") val user: UserModel = UserModel()
) : BaseResponse() {
    data class UserModel(
        @SerializedName("avatar") val avatar: String = "",
        @SerializedName("display_name") val displayName: String = "",
        @SerializedName("followers") val followers: Int = 0,
        @SerializedName("following") val following: Int = 0,
        @SerializedName("id") val id: Int = -1,
        @SerializedName("username") val username: String = "",
    )
}

fun GetUserInfoResponse.UserModel.toUser(): User {
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
