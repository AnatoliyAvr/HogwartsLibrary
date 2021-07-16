package com.loftblog.hogwartslibrary.domain.repositories

import com.loftblog.hogwartslibrary.data.network.RetrofitFactory
import com.loftblog.hogwartslibrary.domain.models.StudentModel
import com.loftblog.hogwartslibrary.domain.models.mapToModel
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
class StudentsRepositoryImpl : StudentsRepository {

  override suspend fun fetchStudents(): List<StudentModel>? {
    return try {
      RetrofitFactory.instance.charactersService.getAllCharacters()
        .filter { it.house.isNotEmpty() }
        .map { it.mapToModel() }
    } catch (e: Exception) {
      null
    }
  }
}