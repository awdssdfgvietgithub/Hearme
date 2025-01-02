package com.app.hearme.network.models

import com.google.gson.annotations.SerializedName
import com.app.hearme.network.BaseResponse

data class  RecommendResponse(
    val topResults: MutableList<ResultModel> = mutableListOf(),
) : BaseResponse() {
    data class ResultModel(
        @SerializedName("score") val score: Double = 0.0,
        @SerializedName("user_id") val userId: String = "",
        @SerializedName("user_predict") val userPredict: SearchUserResponse.UserModel = SearchUserResponse.UserModel(),
    )
}
