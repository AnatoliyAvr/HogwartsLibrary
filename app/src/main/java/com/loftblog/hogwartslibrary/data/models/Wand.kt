package com.loftblog.hogwartslibrary.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Wand(
    val core: String = "",
    val length: String = "",
    val wood: String = ""
)