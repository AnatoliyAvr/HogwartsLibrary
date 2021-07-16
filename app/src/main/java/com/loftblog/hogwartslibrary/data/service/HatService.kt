package com.loftblog.hogwartslibrary.data.service

import com.loftblog.hogwartslibrary.data.models.SpellRemote
import retrofit2.http.GET

interface HatService {

  @GET("./sortingHat")
  suspend fun sortingHat(): String
}