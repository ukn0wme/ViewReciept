package com.example.viewreciept.data.network

import com.example.viewreciept.data.model.entity.AreasResponse
import retrofit2.http.Query
import com.example.viewreciept.data.model.entity.MealsResponse
import retrofit2.http.GET
import retrofit2.Response

interface ApiServiceInterface {

    //search.php?s=Arrabiata
    @GET("search.php")
    suspend fun searchByName(
        @Query("s") mealName:String
    ) : Response<MealsResponse>

    //lookup.php?i=52772
    @GET("lookup.php")
    suspend fun searchById(
        @Query("i") mealId:String
    ) : Response<MealsResponse>

    //filter.php?a=Canadian
    @GET("filter.php")
    suspend fun filterByArea(
        @Query("a") mealId:String
    ) : Response<MealsResponse>

    //list.php?a=list
    @GET("list.php")
    suspend fun listAreas(
        @Query("a") list: String = "list"
    ) : Response<AreasResponse>
}