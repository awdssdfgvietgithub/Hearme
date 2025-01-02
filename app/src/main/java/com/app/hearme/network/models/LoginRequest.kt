package com.app.hearme.network.models

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("username") var username: String = "",
    @SerializedName("password") var password: String = ""
)
