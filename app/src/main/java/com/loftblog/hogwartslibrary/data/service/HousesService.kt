package com.loftblog.hogwartslibrary.data.service

import com.loftblog.hogwartslibrary.data.models.HouseRemote
import retrofit2.http.GET
import retrofit2.http.Path

interface HousesService {

  @GET("./house/{house}")
  suspend fun getHouse(@Path("house") house: String): List<HouseRemote>
}