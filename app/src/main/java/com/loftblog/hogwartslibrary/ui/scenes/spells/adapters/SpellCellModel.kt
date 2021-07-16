package com.loftblog.hogwartslibrary.ui.scenes.spells.adapters

import com.loftblog.hogwartslibrary.domain.models.SpellModel

data class SpellCellModel(val name: String, val type: String)

fun SpellModel.mapToUI(): SpellCellModel {
  return SpellCellModel(name = this.name, type = this.species)
}