package com.app.hearme.network.models

import com.google.gson.annotations.SerializedName

data class SearchUserRequest(
    @SerializedName("username") var query: String = "",
    @SerializedName("page") var page: Int = 1,
    @SerializedName("per_page") var perPage: Int = 20,
)
