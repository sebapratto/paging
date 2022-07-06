package com.sebapp.challengeteco.data.services

import com.sebapp.challengeteco.domain.model.RickAndMortyList
import retrofit2.http.GET
import retrofit2.http.Query


interface RetroService {

    @GET("character")
    suspend fun getDataFromAPI(@Query("page") query: Int): RickAndMortyList
}