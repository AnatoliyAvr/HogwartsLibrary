package com.loftblog.hogwartslibrary.data.service

import com.loftblog.hogwartslibrary.data.models.HouseRemote
import retrofit2.http.GET
import retrofit2.http.Path

interface HousesService {

  companion object {
    val griffindor = "Gryffindor"
    val ravenclaw = "Ravenclaw"
    val slytherin = "Slytherin"
    val hufflepuff = "Hufflepuff"
  }

  @GET("characters/house/{house}")
  suspend fun getHouseDetails(@Path("house") house: String): List<HouseRemote>
}