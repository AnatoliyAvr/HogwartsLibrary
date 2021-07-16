package com.loftblog.hogwartslibrary.domain.repositories

import com.loftblog.hogwartslibrary.data.network.RetrofitFactory
import com.loftblog.hogwartslibrary.domain.models.FacultyModel
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
class HatRepositoryImpl : HatRepository {

  override suspend fun generateFaculty(username: String): FacultyModel {
    return FacultyModel(name = RetrofitFactory.instance.hatService.sortingHat())
  }
}