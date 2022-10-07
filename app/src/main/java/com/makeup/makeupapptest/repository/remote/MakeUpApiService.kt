package com.makeup.makeupapptest.repository.remote

import com.makeup.makeupapptest.home.models.ProductListItem
import retrofit2.Response
import retrofit2.http.GET

interface MakeUpApiService {
    @GET("/api/v1/products.json")
    suspend fun getMakeUp(): Response<List<ProductListItem>>
}