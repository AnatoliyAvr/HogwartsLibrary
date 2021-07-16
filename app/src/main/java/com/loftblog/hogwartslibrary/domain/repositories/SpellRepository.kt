package com.loftblog.hogwartslibrary.domain.repositories

import com.loftblog.hogwartslibrary.domain.models.SpellModel

interface SpellRepository {
  suspend fun getAllSpell(): List<SpellModel>?
//  suspend fun getAllSpell(): List<SpellModel>
}