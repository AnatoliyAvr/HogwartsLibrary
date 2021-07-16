package com.loftblog.hogwartslibrary.domain.models

import com.loftblog.hogwartslibrary.data.models.SpellRemote

data class SpellModel(val name: String, val species: String)

fun SpellRemote.mapToDomain(): SpellModel {
  return SpellModel(name = this.name, species = this.species)
}