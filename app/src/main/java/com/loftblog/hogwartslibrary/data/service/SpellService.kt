package com.loftblog.hogwartslibrary.data.service

import com.loftblog.hogwartslibrary.data.models.SpellRemote
import retrofit2.http.GET

interface SpellService {

  @GET("./characters/staff")
  suspend fun getAllSpell(): List<SpellRemote>
}