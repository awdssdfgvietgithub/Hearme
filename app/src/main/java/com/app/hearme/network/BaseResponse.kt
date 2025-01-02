package com.app.hearme.network

import com.google.gson.annotations.SerializedName

open class BaseResponse(
    @SerializedName("status") val status: String = "",
    @SerializedName("message") val message: String = ""
)
