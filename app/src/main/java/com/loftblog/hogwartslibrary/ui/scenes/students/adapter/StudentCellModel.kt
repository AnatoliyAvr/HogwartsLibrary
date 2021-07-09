package com.loftblog.hogwartslibrary.ui.scenes.students.adapter

import com.loftblog.hogwartslibrary.domain.models.StudentModel

data class StudentCellModel(val name: String, val facultyName: String, val species: String)

fun StudentModel.mapToUI(): StudentCellModel {
  return StudentCellModel(name = this.name, facultyName = this.facultyName, species = this.species)
}