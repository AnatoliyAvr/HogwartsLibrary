package com.loftblog.hogwartslibrary.domain.models

import com.loftblog.hogwartslibrary.data.models.HouseRemote

data class HouseModel(val name: String, val leader: String, val founder: String, val ghost: String)

fun HouseRemote.mapToDomain(): HouseModel {
  return HouseModel(name = this.house, leader = this.name, founder = this.gender, ghost = this.actor)
}