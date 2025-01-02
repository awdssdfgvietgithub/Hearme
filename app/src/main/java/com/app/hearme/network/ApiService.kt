package com.app.hearme.network

import com.app.hearme.network.models.GetUserInfoResponse
import com.app.hearme.network.models.LoginRequest
import com.app.hearme.network.models.LoginResponse
import com.app.hearme.network.models.RecommendResponse
import com.app.hearme.network.models.SearchUserRequest
import com.app.hearme.network.models.SearchUserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @POST("login")
    suspend fun executeLogin(@Body request: LoginRequest): Response<LoginResponse>

    @GET("get_info_user/{userId}")
    suspend fun fetchUserInfo(@Path("userId") userId: Int): Response<GetUserInfoResponse>

    @POST("search_user")
    suspend fun executeSearchUser(@Body request: SearchUserRequest): Response<SearchUserResponse>

    @GET("recommend")
    suspend fun fetchRecommend(
        @Query("input_node") userId: Int,
        @Query("top_k") topK: Int
    ): Response<List<RecommendResponse.ResultModel>>
}