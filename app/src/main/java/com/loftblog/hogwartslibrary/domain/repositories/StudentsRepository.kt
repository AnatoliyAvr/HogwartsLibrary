package com.loftblog.hogwartslibrary.domain.repositories

import com.loftblog.hogwartslibrary.domain.models.StudentModel

interface StudentsRepository {
  suspend fun fetchStudents(): List<StudentModel>?
}