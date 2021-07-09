package com.loftblog.hogwartslibrary.domain.models

import com.loftblog.hogwartslibrary.data.models.CharacterRemote

data class StudentModel(val name: String, val facultyName: String, val species: String)

fun CharacterRemote.mapToModel(): StudentModel {
  return StudentModel(name = this.name, facultyName = this.house, species = this.species)
}
