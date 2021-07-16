package com.loftblog.hogwartslibrary.domain.repositories

import com.loftblog.hogwartslibrary.data.network.RetrofitFactory
import com.loftblog.hogwartslibrary.domain.models.SpellModel
import com.loftblog.hogwartslibrary.domain.models.mapToDomain
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
class SpellRepositoryImpl : SpellRepository {

  override suspend fun getAllSpell(): List<SpellModel>? {
    return try {
      RetrofitFactory.instance.spellService.getAllSpell()
        .map { it.mapToDomain() }
    } catch (e: Exception) {
      null
    }
  }

//  override suspend fun getAllSpell(): List<SpellModel> {
//    return RetrofitFactory.instance.spellService.getAllSpell()
//      .map { it.mapToDomain() }
//  }
}