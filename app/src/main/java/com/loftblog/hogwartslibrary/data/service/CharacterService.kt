package com.loftblog.hogwartslibrary.data.service

import com.loftblog.hogwartslibrary.data.models.CharacterRemote
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterService {

  @GET("./characters/students")
  suspend fun getAllCharacters(): List<CharacterRemote>
}