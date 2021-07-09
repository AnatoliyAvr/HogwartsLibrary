package com.loftblog.hogwartslibrary.domain.repositories

import com.loftblog.hogwartslibrary.domain.models.FacultyModel

interface HatRepository {
  suspend fun generateFaculty(username: String): FacultyModel
}