package com.loftblog.hogwartslibrary.data.models

import kotlinx.serialization.Serializable

@Serializable
data class CharacterRemote(
  val actor: String = "",
  val alive: Boolean,
  val ancestry: String = "",
  val dateOfBirth: String? = null,
  val eyeColour: String = "",
  val gender: String = "",
  val hairColour: String = "",
  val hogwartsStaff: Boolean,
  val hogwartsStudent: Boolean,
  val house: String = "",
  val image: String = "",
  val name: String = "",
  val patronus: String = "",
  val species: String = "",
  val wand: Wand,
  val yearOfBirth: String = ""
)
