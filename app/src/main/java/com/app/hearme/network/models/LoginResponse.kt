package com.app.hearme.network.models

import com.google.gson.annotations.SerializedName
import com.app.hearme.network.BaseResponse

data class LoginResponse(
    @SerializedName("user") val user: UserModel = UserModel()
): BaseResponse() {
    data class UserModel(
        @SerializedName("display_name") val displayName: String = "",
        @SerializedName("id") val id: Int = -1,
        @SerializedName("username") val username: String = "",
        @SerializedName("avatar") val avatar: String = "",
    )
}
